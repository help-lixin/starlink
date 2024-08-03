package help.lixin.starlink.plugin.credential.service.impl;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jasypt.encryption.StringEncryptor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.credential.convert.CredentialServiceConvert;
import help.lixin.starlink.plugin.credential.domain.*;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueCreateCredentialEvent;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueDeleteCredentialEvent;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.dto.*;
import help.lixin.starlink.plugin.credential.event.opaque.OpaqueCredentialEvent;
import help.lixin.starlink.plugin.credential.mapper.*;
import help.lixin.starlink.plugin.credential.mq.provider.opaque.SysCredentialCreateOpaqueEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.opaque.SysCredentialDeleteOpaqueEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialCreateUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialDeleteUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialUpdateUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialCreateSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialDeleteSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialUpdateSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialCreateSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialDeleteSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialUpdateSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialCreateTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialDeleteTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialUpdateTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialCreateTokenEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialDeleteTokenEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialUpdateTokenEventPublish;
import help.lixin.starlink.plugin.credential.service.ICredentialService;

public class CredentialService implements ICredentialService {

    private Logger logger = LoggerFactory.getLogger(CredentialService.class);
    private SysCredentialCreateUserNamePasswordEventPublish createUserNamePasswordEventPublish;
    private SysCredentialUpdateUserNamePasswordEventPublish updateUserNamePasswordEventPublish;
    private SysCredentialDeleteUserNamePasswordEventPublish deleteUserNamePasswordEventPublish;

    private SysCredentialCreateSecretEventPublish createSecretEventPublish;
    private SysCredentialUpdateSecretEventPublish updateSecretEventPublish;
    private SysCredentialDeleteSecretEventPublish deleteSecretEventPublish;

    private SysCredentialCreateSshEventPublish createSshEventPublish;
    private SysCredentialUpdateSshEventPublish updateSshEventPublish;
    private SysCredentialDeleteSshEventPublish deleteSshEventPublish;

    private SysCredentialCreateTokenEventPublish createTokenEventPublish;
    private SysCredentialUpdateTokenEventPublish updateTokenEventPublish;
    private SysCredentialDeleteTokenEventPublish deleteTokenEventPublish;

    private SysCredentialCreateTLSEventPublish createTLSEventPublish;
    private SysCredentialUpdateTLSEventPublish updateTLSEventPublish;
    private SysCredentialDeleteTLSEventPublish deleteTLSEventPublish;

    private SysCredentialCreateOpaqueEventPublish createOpaqueEventPublish;
    private SysCredentialDeleteOpaqueEventPublish deleteOpaqueEventPublish;

    private QueryTemplate queryTemplate;

    private SysCredentialTextMapper sysCredentialTextMapper;
    private SysCredentialsSshMapper sysCredentialsSshMapper;
    private SysCredentialsUsernamePasswordMapper sysCredentialsUsernamePasswordMapper;
    private SysCredentialCommonMapper sysCredentialCommonMapper;
    private SysCredentialTokenMapper sysCredentialTokenMapper;
    private SysCredentialTlsMapper sysCredentialTlsMapper;
    private SysCredentialOpaqueMapper sysCredentialOpaqueMapper;
    private SysCredentialNamespacesMapper sysCredentialNamespacesMapper;

    private StringEncryptor stringEncryptor;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean save(SysCredentialCommonDTO sysCredentialCommonDTO) {

        CredentialServiceConvert commonMapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysCredentialCommon sysCredentialCommon = commonMapper.convertDomain(sysCredentialCommonDTO);
        Long id = sysCredentialCommon.getId();

        QueryWrapper<SysCredentialCommon> queryWrapper = new QueryWrapper();
        queryWrapper.eq("credential_key", sysCredentialCommon.getCredentialKey());
        queryWrapper.eq("instance_code", sysCredentialCommon.getInstanceCode());
        queryWrapper.eq("is_del", 0);
        SysCredentialCommon checkKey = sysCredentialCommonMapper.selectOne(queryWrapper);

        if (checkKey != null && id == null) {
            throw new ServiceException("凭证key已存在，请检查后再添加");
        }

        // 保存凭证信息
        if (id == null) {
            sysCredentialCommon.setUpdateBy(sysCredentialCommon.getCreateBy());
            sysCredentialCommonMapper.insertCommon(sysCredentialCommon);
        } else {

            SysCredentialCommon sysCredentialCommonDB = sysCredentialCommonMapper.selectById(id);

            if (sysCredentialCommonDB == null) {
                throw new ServiceException("数据库不存在SysCredentialCommon的id，请校验后再更新");
            }

            if (checkKey != null && !sysCredentialCommonDB.getCredentialKey().equals(checkKey.getCredentialKey())) {
                throw new ServiceException("凭证key已存在，请检查后再添加");
            }

            sysCredentialCommon.setCreateBy(sysCredentialCommonDB.getCreateBy());
            sysCredentialCommon.setCreateTime(sysCredentialCommonDB.getCreateTime());
            sysCredentialCommon.setUpdateBy(sysCredentialCommonDTO.getCreateBy());
            sysCredentialCommon.setUpdateTime(new Date());

            sysCredentialCommonMapper.updateById(sysCredentialCommon);
        }

        // 保存用户名密码凭证
        if (sysCredentialCommonDTO instanceof SysCredentialUsernamePasswordDTO) {

            SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO =
                (SysCredentialUsernamePasswordDTO)sysCredentialCommonDTO;
            // 加密
            sysCredentialUsernamePasswordDTO
                .setPassword(stringEncryptor.encrypt(sysCredentialUsernamePasswordDTO.getPassword()));

            CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
            SysCredentialUsernamePassword sysCredentialUsernamePassword =
                mapper.convertDomain(sysCredentialUsernamePasswordDTO);
            sysCredentialUsernamePassword.setId(sysCredentialCommon.getId());

            if (id == null) {
                sysCredentialsUsernamePasswordMapper.insertSelective(sysCredentialUsernamePassword);

                publishCreateUsernamePassword(sysCredentialUsernamePasswordDTO);
            } else {
                SysCredentialUsernamePassword sysCredentialUsernamePasswordDB =
                    sysCredentialsUsernamePasswordMapper.queryById(id);
                if (sysCredentialUsernamePasswordDB == null) {
                    throw new ServiceException("数据库不存在SysCredentialsUsernamePassword的id，请校验后再更新");
                }

                sysCredentialUsernamePassword.setCreateTime(sysCredentialUsernamePasswordDB.getCreateTime());
                sysCredentialUsernamePassword.setUpdateTime(new Date());
                sysCredentialsUsernamePasswordMapper.updateByPrimaryKeySelective(sysCredentialUsernamePassword);

                publishUpdateUsernamePassword(sysCredentialUsernamePasswordDTO);
            }

        } else if (sysCredentialCommonDTO instanceof SysCredentialSshDTO) {
            // 保存SSH凭证

            SysCredentialSshDTO sysCredentialSshDTO = (SysCredentialSshDTO)sysCredentialCommonDTO;
            // 加密
            sysCredentialSshDTO.setPrivateKey(stringEncryptor.encrypt(sysCredentialSshDTO.getPrivateKey()));
            sysCredentialSshDTO.setPassphrase(stringEncryptor.encrypt(sysCredentialSshDTO.getPassphrase()));

            CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
            SysCredentialSsh sysCredentialSsh = mapper.convertDomain(sysCredentialSshDTO);

            sysCredentialSsh.setId(sysCredentialCommon.getId());

            if (id == null) {
                sysCredentialsSshMapper.insertSelective(sysCredentialSsh);

                publishCreateSSH(sysCredentialSshDTO);
            } else {
                SysCredentialSsh sysCredentialSshDB = sysCredentialsSshMapper.queryById(id);
                if (sysCredentialSshDB == null) {
                    throw new ServiceException("数据库不存在SysCredentialsSsh的id，请校验后再更新");
                }
                sysCredentialSsh.setCreateTime(sysCredentialSshDB.getCreateTime());
                sysCredentialSsh.setUpdateTime(new Date());
                sysCredentialsSshMapper.updateByPrimaryKeySelective(sysCredentialSsh);

                publishUpdateSSH(sysCredentialSshDTO);
            }

        } else if (sysCredentialCommonDTO instanceof SysCredentialTextDTO) {
            // 保存Secret凭证

            SysCredentialTextDTO sysCredentialTextDTO = (SysCredentialTextDTO)sysCredentialCommonDTO;
            // 加密
            sysCredentialTextDTO.setPassword(stringEncryptor.encrypt(sysCredentialTextDTO.getPassword()));

            CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
            SysCredentialText sysCredentialText = mapper.convertDomain(sysCredentialTextDTO);

            sysCredentialText.setId(sysCredentialCommon.getId());

            if (id == null) {
                sysCredentialTextMapper.insertSelective(sysCredentialText);

                publishCreateSecret(sysCredentialTextDTO);
            } else {
                SysCredentialText sysCredentialTextDB = sysCredentialTextMapper.selectById(id);
                if (sysCredentialTextDB == null) {
                    throw new ServiceException("数据库不存在SysCredentialText的id，请校验后再更新");
                }
                sysCredentialText.setCreateTime(sysCredentialTextDB.getCreateTime());
                sysCredentialText.setUpdateTime(new Date());
                sysCredentialTextMapper.updateByPrimaryKeySelective(sysCredentialTextDB);

                publishUpdateSecret(sysCredentialTextDTO);
            }

        } else if (sysCredentialCommonDTO instanceof SysCredentialTokenDTO) {
            // 保存Token凭证

            SysCredentialTokenDTO sysCredentialTokenDTO = (SysCredentialTokenDTO)sysCredentialCommonDTO;
            // 加密
            sysCredentialTokenDTO.setToken(stringEncryptor.encrypt(sysCredentialTokenDTO.getToken()));

            CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
            SysCredentialToken sysCredentialToken = mapper.convertDomain(sysCredentialTokenDTO);

            sysCredentialToken.setId(sysCredentialCommon.getId());

            if (id == null) {
                sysCredentialTokenMapper.insertSelective(sysCredentialToken);

                publishCreateToken(sysCredentialTokenDTO);
            } else {
                SysCredentialToken sysCredentialTokenDB = sysCredentialTokenMapper.queryById(id);
                if (sysCredentialTokenDB == null) {
                    throw new ServiceException("数据库不存在SysCredentialToken的id，请校验后再更新");
                }
                sysCredentialToken.setCreateTime(sysCredentialTokenDB.getCreateTime());
                sysCredentialToken.setUpdateTime(new Date());
                sysCredentialTokenMapper.updateByPrimaryKeySelective(sysCredentialToken);

                publishUpdateToken(sysCredentialTokenDTO);
            }

        } else if (sysCredentialCommonDTO instanceof SysCredentialTlsDTO) {
            // 保存K8S凭证

            SysCredentialTlsDTO sysCredentialTlsDTO = (SysCredentialTlsDTO)sysCredentialCommonDTO;
            // 私钥加密
            sysCredentialTlsDTO.setPrivateKey(stringEncryptor.encrypt(sysCredentialTlsDTO.getPrivateKey()));

            // 证书加密
            sysCredentialTlsDTO.setCertificate(stringEncryptor.encrypt(sysCredentialTlsDTO.getCertificate()));

            CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
            SysCredentialTls sysCredentialTls = mapper.convertDomain(sysCredentialTlsDTO);

            sysCredentialTls.setId(sysCredentialCommon.getId());

            if (id == null) {
                sysCredentialTlsMapper.insertSelective(sysCredentialTls);

                publishCreateTLS(sysCredentialTlsDTO);
            } else {
                SysCredentialTls credentialTls = sysCredentialTlsMapper.queryById(id);
                if (credentialTls == null) {
                    throw new ServiceException("数据库不存在TLS的id，请校验后再更新");
                }
                sysCredentialTlsMapper.updateById(sysCredentialTls);

                publishUpdateTLS(sysCredentialTlsDTO);
            }

        } else if (sysCredentialCommonDTO instanceof SysCredentialOpaqueDTO) {

            SysCredentialOpaqueDTO sysCredentialOpaqueDTO = (SysCredentialOpaqueDTO)sysCredentialCommonDTO;

            CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
            SysCredentialOpaque sysCredentialOpaque = mapper.convertDomain(sysCredentialOpaqueDTO);

            sysCredentialOpaqueMapper.deleteById(sysCredentialOpaque.getId());

            sysCredentialOpaqueDTO.getDataList().forEach((v) -> {
                sysCredentialOpaque.setOpqKey(v.getLabel());
                String encryptValue = stringEncryptor.encrypt(v.getValue());
                v.setValue(encryptValue);

                // 值加密
                sysCredentialOpaque.setOpqValue(encryptValue);
                sysCredentialOpaque.setCommonId(sysCredentialCommon.getId());
                sysCredentialOpaqueMapper.insertSelective(sysCredentialOpaque);
            });

            publishCreateOpaque(sysCredentialOpaqueDTO);

        }

        return true;
    }

    @Override
    public List<SysCredentialOptionDTO> credentialOptionSelect(String instanceCode) {
        List<SysCredentialOptionDTO> resultList = new ArrayList<>();

        List<SysCredentialCommon> sysCredentialCommons =
            sysCredentialCommonMapper.queryListByInstanceCode(instanceCode);
        sysCredentialCommons.forEach(i -> {
            SysCredentialOptionDTO sysCredentialOptionDTO = new SysCredentialOptionDTO();
            sysCredentialOptionDTO.setValue(i.getId());
            sysCredentialOptionDTO.setLabel(i.getCredentialName());
            resultList.add(sysCredentialOptionDTO);
        });
        return resultList;
    }

    /**
     * @Param instanceCode : 必填
     * @Param nameSpace : 必填
     * @Param filterType : 需要过滤的凭证类型（非必填）
     * @Param isFilterURL : （非必填）
     * @Author: 伍岳林
     * @Date: 2024/6/7 下午12:04
     * @Return: java.util.List<help.lixin.starlink.plugin.credential.dto.CredentialOptionDTO>
     * @Description
     */
    @Override
    public List<CredentialOptionDTO> credentialOptionSelect(String instanceCode, String nameSpace, String filterType,
        Boolean isFilterURL) {
        List<CredentialOptionDTO> resultList = new ArrayList<>();

        List<Map> maps = sysCredentialCommonMapper.queryOptionList(instanceCode, nameSpace, filterType);
        List<SysCredentialCommon> sysCredentialCommons = convertToSysCredentialCommon(false, maps);
        sysCredentialCommons.forEach(i -> {

            CredentialOptionDTO credentialOptionDTO = new CredentialOptionDTO();

            if (filterType == null) {
                credentialOptionDTO.setValue(i.getCredentialKey());
                credentialOptionDTO.setLabel(i.getCredentialKey());
            } else if (filterType.equals(i.getCredentialType())) {
                // 当前是账号名密码类型，URL过滤为true，并且镜像地址为空则中断
                if (i instanceof SysCredentialUsernamePassword && isFilterURL) {
                    SysCredentialUsernamePassword sysCredentialUsernamePassword = (SysCredentialUsernamePassword)i;
                    if (sysCredentialUsernamePassword.getImgDomain() == null) {
                        return;
                    }
                }
                credentialOptionDTO.setValue(i.getCredentialKey());
                credentialOptionDTO.setLabel(i.getCredentialKey());
            } else {
                return;
            }
            resultList.add(credentialOptionDTO);
        });
        return resultList;
    }

    @Override
    public Boolean syncAllCredential() {
        List<SysCredentialCommon> allCredential = sysCredentialCommonMapper.selectAll();

        for (SysCredentialCommon sysCredentialCommon : allCredential) {
            try {
                SysCredentialCommon credentialCommon = queryOriginDataById(sysCredentialCommon.getId());
                if (credentialCommon.getCredentialType().equals(TOKEN)) {
                    CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
                    SysTokenCredentialCreateEvent token = mapper.createToken((SysCredentialToken)credentialCommon);
                    createTokenEventPublish.createToken(token);
                } else if (credentialCommon.getCredentialType().equals(USERNAME_PASSWORD)) {
                    CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
                    SysUserNamePasswordCredentialCreateEvent pwd =
                        mapper.createPwd((SysCredentialUsernamePassword)credentialCommon);
                    createUserNamePasswordEventPublish.createUserNamePassword(pwd);
                } else if (credentialCommon.getCredentialType().equals(SECRET)) {
                    CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
                    SysSecretCredentialCreateEvent secret = mapper.createSecret((SysCredentialText)credentialCommon);
                    createSecretEventPublish.createSecret(secret);
                } else if (credentialCommon.getCredentialType().equals(SSH)) {
                    CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
                    SysSshCredentialCreateEvent ssh = mapper.createSsh((SysCredentialSsh)credentialCommon);
                    createSshEventPublish.createSsh(ssh);
                }
            } catch (Exception e) {
                logger.error("同步数据发生异常:[{}]", e.getMessage());
            }
        }
        return true;
    }

    @Override
    public PageResponse<SysCredentialCommon> list(SysCredentialPageListDTO sysCredentialPageListDTO) {
        return queryTemplate.execute(sysCredentialPageListDTO, () -> {
            sysCredentialCommonMapper.list(sysCredentialPageListDTO);
        });
    }

    @Override
    public SysCredentialCommon queryDecodeById(Long id) {
        List<Map> maps = sysCredentialCommonMapper.queryListById(id);
        List<SysCredentialCommon> sysCredentialCommonList = convertToSysCredentialCommon(true, maps);
        if (CollectionUtils.isEmpty(sysCredentialCommonList)) {
            throw new ServiceException("暂无该数据：" + id);
        }

        return sysCredentialCommonList.get(0);
    }

    @Override
    public SysCredentialCommon queryOriginDataById(Long id) {

        List<Map> maps = sysCredentialCommonMapper.queryListById(id);
        List<SysCredentialCommon> sysCredentialCommonList = convertToSysCredentialCommon(false, maps);

        if (CollectionUtils.isEmpty(sysCredentialCommonList)) {
            throw new ServiceException("暂无该数据：" + id);
        }

        return sysCredentialCommonList.get(0);
    }

    /**
     * @Param isDecrypt : 是否返回解密信息(true:返回解密信息)
     * @Param maps :
     * @Author: 伍岳林
     * @Date: 2024/3/31 下午3:41
     * @Return: java.util.List<help.lixin.starlink.plugin.credential.domain.SysCredentialCommon>
     * @Description
     */
    protected List<SysCredentialCommon> convertToSysCredentialCommon(Boolean isDecrypt, List<Map> maps) {
        List<SysCredentialCommon> resultList = new ArrayList<>();
        for (Map map : maps) {
            String credentialType = (String)map.get("credential_type");
            if (credentialType.equals(TOKEN)) {
                SysCredentialToken token = new SysCredentialToken();
                token.setId((Long)map.get("id"));
                token.setUserName((String)map.get("value_1"));

                if (isDecrypt) {
                    // 解密
                    token.setToken(stringEncryptor.decrypt((String)map.get("value_2")));
                } else {
                    token.setToken((String)map.get("value_2"));
                }
                token.setCreateBy((String)map.get("create_by"));
                token.setCreateTime((Date)map.get("create_time"));
                token.setCredentialKey((String)map.get("credential_key"));
                token.setCredentialName((String)map.get("credential_name"));
                token.setInstanceCode((String)map.get("instance_code"));
                token.setNameSpace((String)map.get("name_space"));
                token.setPluginCode((String)map.get("plugin_code"));
                token.setRemark((String)map.get("remark"));
                token.setStatus((Integer)map.get("status"));
                token.setUpdateBy((String)map.get("update_by"));
                token.setUpdateTime((Date)map.get("update_time"));
                token.setCredentialType((String)map.get("credential_type"));

                resultList.add(token);

            } else if (credentialType.equals(USERNAME_PASSWORD)) {
                SysCredentialUsernamePassword usernamePassword = new SysCredentialUsernamePassword();
                usernamePassword.setId((Long)map.get("id"));
                usernamePassword.setUserName((String)map.get("value_1"));
                if (isDecrypt) {
                    // 解密
                    usernamePassword.setPassword(stringEncryptor.decrypt((String)map.get("value_2")));
                } else {
                    usernamePassword.setPassword((String)map.get("value_2"));
                }
                usernamePassword.setImgDomain((String)map.get("value_3"));

                usernamePassword.setCreateBy((String)map.get("create_by"));
                usernamePassword.setCreateTime((Date)map.get("create_time"));
                usernamePassword.setCredentialKey((String)map.get("credential_key"));
                usernamePassword.setCredentialName((String)map.get("credential_name"));
                usernamePassword.setNameSpace((String)map.get("name_space"));
                usernamePassword.setInstanceCode((String)map.get("instance_code"));
                usernamePassword.setPluginCode((String)map.get("plugin_code"));
                usernamePassword.setRemark((String)map.get("remark"));
                usernamePassword.setStatus((Integer)map.get("status"));
                usernamePassword.setUpdateBy((String)map.get("update_by"));
                usernamePassword.setUpdateTime((Date)map.get("update_time"));
                usernamePassword.setCredentialType((String)map.get("credential_type"));

                resultList.add(usernamePassword);
            } else if (credentialType.equals(SECRET)) {
                SysCredentialText text = new SysCredentialText();
                text.setId((Long)map.get("id"));

                if (isDecrypt) {
                    // 解密
                    text.setSecret(stringEncryptor.decrypt((String)map.get("value_1")));
                    text.setPassword(stringEncryptor.decrypt((String)map.get("value_2")));
                } else {
                    text.setSecret((String)map.get("value_1"));
                    text.setPassword((String)map.get("value_2"));
                }
                text.setCreateBy((String)map.get("create_by"));
                text.setCreateTime((Date)map.get("create_time"));
                text.setCredentialKey((String)map.get("credential_key"));
                text.setCredentialName((String)map.get("credential_name"));
                text.setNameSpace((String)map.get("name_space"));
                text.setInstanceCode((String)map.get("instance_code"));
                text.setPluginCode((String)map.get("plugin_code"));
                text.setRemark((String)map.get("remark"));
                text.setStatus((Integer)map.get("status"));
                text.setUpdateBy((String)map.get("update_by"));
                text.setUpdateTime((Date)map.get("update_time"));
                text.setCredentialType((String)map.get("credential_type"));

                resultList.add(text);
            } else if (credentialType.equals(SSH)) {
                SysCredentialSsh ssh = new SysCredentialSsh();
                ssh.setId((Long)map.get("id"));
                ssh.setUserName((String)map.get("value_1"));

                if (isDecrypt) {
                    // 解密
                    ssh.setPassphrase(stringEncryptor.decrypt((String)map.get("value_2")));
                    ssh.setPrivateKey(stringEncryptor.decrypt((String)map.get("value_3")));
                } else {
                    ssh.setPassphrase((String)map.get("value_2"));
                    ssh.setPrivateKey((String)map.get("value_3"));
                }

                ssh.setPublicKey((String)map.get("value_4"));
                ssh.setCreateBy((String)map.get("create_by"));
                ssh.setCreateTime((Date)map.get("create_time"));
                ssh.setCredentialKey((String)map.get("credential_key"));
                ssh.setCredentialName((String)map.get("credential_name"));
                ssh.setNameSpace((String)map.get("name_space"));
                ssh.setInstanceCode((String)map.get("instance_code"));
                ssh.setPluginCode((String)map.get("plugin_code"));
                ssh.setRemark((String)map.get("remark"));
                ssh.setStatus((Integer)map.get("status"));
                ssh.setUpdateBy((String)map.get("update_by"));
                ssh.setUpdateTime((Date)map.get("update_time"));
                ssh.setCredentialType((String)map.get("credential_type"));

                resultList.add(ssh);
            } else if (credentialType.equals(TLS)) {
                SysCredentialTls tls = new SysCredentialTls();
                tls.setId((Long)map.get("id"));

                if (isDecrypt) {
                    // 解密
                    tls.setCertificate(stringEncryptor.decrypt((String)map.get("value_1")));
                    tls.setPrivateKey(stringEncryptor.decrypt((String)map.get("value_2")));
                } else {
                    tls.setCertificate((String)map.get("value_1"));
                    tls.setPrivateKey((String)map.get("value_2"));
                }

                tls.setCreateBy((String)map.get("create_by"));
                tls.setCreateTime((Date)map.get("create_time"));
                tls.setCredentialKey((String)map.get("credential_key"));
                tls.setCredentialName((String)map.get("credential_name"));
                tls.setNameSpace((String)map.get("name_space"));
                tls.setInstanceCode((String)map.get("instance_code"));
                tls.setPluginCode((String)map.get("plugin_code"));
                tls.setRemark((String)map.get("remark"));
                tls.setStatus((Integer)map.get("status"));
                tls.setUpdateBy((String)map.get("update_by"));
                tls.setUpdateTime((Date)map.get("update_time"));
                tls.setCredentialType((String)map.get("credential_type"));

                resultList.add(tls);

            } else if (credentialType.equals(OPAQUE)) {
                SysCredentialOpaque opaque = new SysCredentialOpaque();

                List<SysCredentialOpaque> sysCredentialOpaques =
                    sysCredentialOpaqueMapper.queryOpaqueList((Long)map.get("id"));

                if (sysCredentialOpaques != null) {
                    sysCredentialOpaques.forEach(v -> {

                        String value;
                        if (isDecrypt) {
                            // 解密
                            value = stringEncryptor.decrypt(v.getOpqValue());
                        } else {
                            value = v.getOpqValue();
                        }

                        CredentialOptionDTO credentialOptionDTO = new CredentialOptionDTO();
                        credentialOptionDTO.setLabel(v.getOpqKey());
                        credentialOptionDTO.setValue(value);
                        opaque.getDataList().add(credentialOptionDTO);
                    });

                }

                opaque.setId((Long)map.get("id"));
                opaque.setCreateBy((String)map.get("create_by"));
                opaque.setCreateTime((Date)map.get("create_time"));
                opaque.setCredentialKey((String)map.get("credential_key"));
                opaque.setCredentialName((String)map.get("credential_name"));
                opaque.setNameSpace((String)map.get("name_space"));
                opaque.setInstanceCode((String)map.get("instance_code"));
                opaque.setPluginCode((String)map.get("plugin_code"));
                opaque.setRemark((String)map.get("remark"));
                opaque.setStatus((Integer)map.get("status"));
                opaque.setUpdateBy((String)map.get("update_by"));
                opaque.setUpdateTime((Date)map.get("update_time"));
                opaque.setCredentialType((String)map.get("credential_type"));

                resultList.add(opaque);
            }
        }
        return resultList;
    }

    @Override
    public Boolean checkId(String credentialKey, String instanceCode) {
        QueryWrapper<SysCredentialCommon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("credential_key", credentialKey);
        queryWrapper.eq("instance_code", instanceCode);
        queryWrapper.eq("is_del", 0);
        SysCredentialCommon sysCredentialCommon = sysCredentialCommonMapper.selectOne(queryWrapper);
        return sysCredentialCommon != null;
    }

    @Override
    public Integer changeStatus(Integer status, Long id, String createBy) {
        QueryWrapper<SysCredentialCommon> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("is_del", 0);
        SysCredentialCommon sysCredentialCommon = sysCredentialCommonMapper.selectOne(queryWrapper);
        if (sysCredentialCommon == null) {
            throw new ServiceException("此数据不存在");
        }
        sysCredentialCommon.setStatus(status);
        sysCredentialCommon.setCreateBy(createBy);
        return sysCredentialCommonMapper.updateById(sysCredentialCommon);
    }

    @Override
    public Boolean removeCredential(Long id) {
        SysCredentialCommon sysCredentialCommon = sysCredentialCommonMapper.selectById(id);
        if (sysCredentialCommon == null) {
            throw new ServiceException("该id不存在");
        }
        if (sysCredentialCommonMapper.removeById(id) > 0) {
            String credentialType = sysCredentialCommon.getCredentialType();
            String instanceCode = sysCredentialCommon.getInstanceCode();
            String credentialKey = sysCredentialCommon.getCredentialKey();
            String pluginCode = sysCredentialCommon.getPluginCode();
            String nameSpace = sysCredentialCommon.getNameSpace();

            if (credentialType.equals(TOKEN)) {
                SysTokenCredentialDeleteEvent deleteCredentialEvent = new SysTokenCredentialDeleteEvent();
                deleteCredentialEvent.setCredentialKey(credentialKey);
                deleteCredentialEvent.setInstanceCode(instanceCode);
                deleteCredentialEvent.setPluginCode(pluginCode);
                deleteCredentialEvent.setNameSpace(nameSpace);
                deleteTokenEventPublish.deleteEvent(deleteCredentialEvent);
            } else if (credentialType.equals(USERNAME_PASSWORD)) {
                SysUserNamePasswordCredentialDeleteEvent deleteCredentialEvent =
                    new SysUserNamePasswordCredentialDeleteEvent();
                deleteCredentialEvent.setCredentialKey(credentialKey);
                deleteCredentialEvent.setInstanceCode(instanceCode);
                deleteCredentialEvent.setPluginCode(pluginCode);
                deleteCredentialEvent.setNameSpace(nameSpace);
                deleteUserNamePasswordEventPublish.deleteEvent(deleteCredentialEvent);
            } else if (credentialType.equals(SECRET)) {
                SysSecretCredentialDeleteEvent deleteCredentialEvent = new SysSecretCredentialDeleteEvent();
                deleteCredentialEvent.setCredentialKey(credentialKey);
                deleteCredentialEvent.setInstanceCode(instanceCode);
                deleteCredentialEvent.setPluginCode(pluginCode);
                deleteCredentialEvent.setNameSpace(nameSpace);
                deleteSecretEventPublish.deleteEvent(deleteCredentialEvent);
            } else if (credentialType.equals(SSH)) {
                SysSshCredentialDeleteEvent deleteCredentialEvent = new SysSshCredentialDeleteEvent();
                deleteCredentialEvent.setCredentialKey(credentialKey);
                deleteCredentialEvent.setInstanceCode(instanceCode);
                deleteCredentialEvent.setPluginCode(pluginCode);
                deleteCredentialEvent.setNameSpace(nameSpace);
                deleteSshEventPublish.deleteEvent(deleteCredentialEvent);
            } else if (credentialType.equals(TLS)) {
                SysTLSCredentialDeleteEvent deleteCredentialEvent = new SysTLSCredentialDeleteEvent();
                deleteCredentialEvent.setCredentialKey(credentialKey);
                deleteCredentialEvent.setInstanceCode(instanceCode);
                deleteCredentialEvent.setPluginCode(pluginCode);
                deleteCredentialEvent.setNameSpace(nameSpace);
                deleteTLSEventPublish.deleteEvent(deleteCredentialEvent);
            } else if (credentialType.equals(OPAQUE)) {
                SysOpaqueDeleteCredentialEvent deleteCredentialEvent = new SysOpaqueDeleteCredentialEvent();
                deleteCredentialEvent.setCredentialKey(credentialKey);
                deleteCredentialEvent.setInstanceCode(instanceCode);
                deleteCredentialEvent.setPluginCode(pluginCode);
                deleteCredentialEvent.setNameSpace(nameSpace);
                deleteOpaqueEventPublish.deleteEvent(deleteCredentialEvent);
            } else {
                throw new ServiceException("该类型不存在");
            }

        } else {
            return false;
        }

        return true;
    }

    @Override
    public List<SysCredentialNameSpaceDTO> queryNameSpaceList(String instanceCode) {
        return sysCredentialNamespacesMapper.queryNameSpaceOptionList(instanceCode);
    }

    private void publishCreateToken(SysCredentialTokenDTO sysCredentialTokenDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysTokenCredentialCreateEvent token = mapper.createToken(sysCredentialTokenDTO);

        createTokenEventPublish.createToken(token);
    }

    private void publishUpdateToken(SysCredentialTokenDTO sysCredentialTokenDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysTokenCredentialUpdateEvent token = mapper.updateToken(sysCredentialTokenDTO);

        updateTokenEventPublish.updateToken(token);
    }

    private void publishCreateTLS(SysCredentialTlsDTO sysCredentialTlsDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysTLSCredentialCreateEvent specialCreateEvent = mapper.createTLS(sysCredentialTlsDTO);

        createTLSEventPublish.createEvent(specialCreateEvent);
    }

    private void publishUpdateTLS(SysCredentialTlsDTO sysCredentialTlsDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysTLSCredentialUpdateEvent credentialSpecialUpdateEvent = mapper.updateTLS(sysCredentialTlsDTO);

        updateTLSEventPublish.updateEvent(credentialSpecialUpdateEvent);
    }

    private void publishCreateOpaque(SysCredentialOpaqueDTO sysCredentialOpaqueDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysOpaqueCreateCredentialEvent mapperOpaque = mapper.createOpaque(sysCredentialOpaqueDTO);

        List<OpaqueCredentialEvent> resultList = new ArrayList<>();
        sysCredentialOpaqueDTO.getDataList().forEach(list -> {
            OpaqueCredentialEvent opaqueCredentialEvent = new OpaqueCredentialEvent();
            opaqueCredentialEvent.setKey(list.getLabel());
            opaqueCredentialEvent.setValue(list.getValue());
            resultList.add(opaqueCredentialEvent);
        });
        mapperOpaque.setDataList(resultList);
        createOpaqueEventPublish.createEvent(mapperOpaque);
    }

    private void publishCreateSecret(SysCredentialTextDTO sysCredentialTextDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysSecretCredentialCreateEvent secret = mapper.createSecret(sysCredentialTextDTO);

        createSecretEventPublish.createSecret(secret);
    }

    private void publishUpdateSecret(SysCredentialTextDTO sysCredentialTextDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysSecretCredentialUpdateEvent secret = mapper.updateSecret(sysCredentialTextDTO);

        updateSecretEventPublish.updateSecret(secret);
    }

    private void publishCreateSSH(SysCredentialSshDTO sysCredentialSshDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysSshCredentialCreateEvent ssh = mapper.createSsh(sysCredentialSshDTO);

        createSshEventPublish.createSsh(ssh);
    }

    private void publishUpdateSSH(SysCredentialSshDTO sysCredentialSshDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysSshCredentialUpdateEvent ssh = mapper.updateSsh(sysCredentialSshDTO);

        updateSshEventPublish.updateSsh(ssh);
    }

    private void publishCreateUsernamePassword(SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysUserNamePasswordCredentialCreateEvent pwd = mapper.createPwd(sysCredentialUsernamePasswordDTO);
        createUserNamePasswordEventPublish.createUserNamePassword(pwd);
    }

    private void publishUpdateUsernamePassword(SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO) {
        CredentialServiceConvert mapper = Mappers.getMapper(CredentialServiceConvert.class);
        SysUserNamePasswordCredentialUpdateEvent pwd = mapper.updatePwd(sysCredentialUsernamePasswordDTO);
        updateUserNamePasswordEventPublish.updateUserNamePassword(pwd);
    }

    public CredentialService(SysCredentialCreateUserNamePasswordEventPublish createUserNamePasswordEventPublish,
        SysCredentialUpdateUserNamePasswordEventPublish updateUserNamePasswordEventPublish,
        SysCredentialDeleteUserNamePasswordEventPublish deleteUserNamePasswordEventPublish,
        SysCredentialCreateSecretEventPublish createSecretEventPublish,
        SysCredentialUpdateSecretEventPublish updateSecretEventPublish,
        SysCredentialDeleteSecretEventPublish deleteSecretEventPublish,
        SysCredentialCreateSshEventPublish createSshEventPublish,
        SysCredentialUpdateSshEventPublish updateSshEventPublish,
        SysCredentialDeleteSshEventPublish deleteSshEventPublish,
        SysCredentialCreateTokenEventPublish createTokenEventPublish,
        SysCredentialUpdateTokenEventPublish updateTokenEventPublish,
        SysCredentialDeleteTokenEventPublish deleteTokenEventPublish,
        SysCredentialCreateTLSEventPublish createTLSEventPublish,
        SysCredentialUpdateTLSEventPublish updateTLSEventPublish,
        SysCredentialDeleteTLSEventPublish deleteTLSEventPublish,
        SysCredentialCreateOpaqueEventPublish createOpaqueEventPublish,
        SysCredentialDeleteOpaqueEventPublish deleteOpaqueEventPublish, SysCredentialTextMapper sysCredentialTextMapper,
        SysCredentialTokenMapper sysCredentialTokenMapper, SysCredentialsSshMapper sysCredentialsSshMapper,
        SysCredentialsUsernamePasswordMapper sysCredentialsUsernamePasswordMapper,
        SysCredentialCommonMapper sysCredentialCommonMapper, SysCredentialTlsMapper sysCredentialTlsMapper,
        SysCredentialOpaqueMapper sysCredentialOpaqueMapper,
        SysCredentialNamespacesMapper sysCredentialNamespacesMapper, QueryTemplate queryTemplate,
        StringEncryptor stringEncryptor) {
        this.createUserNamePasswordEventPublish = createUserNamePasswordEventPublish;
        this.updateUserNamePasswordEventPublish = updateUserNamePasswordEventPublish;
        this.deleteUserNamePasswordEventPublish = deleteUserNamePasswordEventPublish;
        this.createSecretEventPublish = createSecretEventPublish;
        this.updateSecretEventPublish = updateSecretEventPublish;
        this.deleteSecretEventPublish = deleteSecretEventPublish;
        this.createSshEventPublish = createSshEventPublish;
        this.updateSshEventPublish = updateSshEventPublish;
        this.deleteSshEventPublish = deleteSshEventPublish;
        this.createTokenEventPublish = createTokenEventPublish;
        this.updateTokenEventPublish = updateTokenEventPublish;
        this.deleteTokenEventPublish = deleteTokenEventPublish;
        this.createTLSEventPublish = createTLSEventPublish;
        this.updateTLSEventPublish = updateTLSEventPublish;
        this.deleteTLSEventPublish = deleteTLSEventPublish;
        this.createOpaqueEventPublish = createOpaqueEventPublish;
        this.deleteOpaqueEventPublish = deleteOpaqueEventPublish;
        this.queryTemplate = queryTemplate;
        this.sysCredentialTextMapper = sysCredentialTextMapper;
        this.sysCredentialsSshMapper = sysCredentialsSshMapper;
        this.sysCredentialsUsernamePasswordMapper = sysCredentialsUsernamePasswordMapper;
        this.sysCredentialCommonMapper = sysCredentialCommonMapper;
        this.sysCredentialTokenMapper = sysCredentialTokenMapper;
        this.sysCredentialTlsMapper = sysCredentialTlsMapper;
        this.sysCredentialOpaqueMapper = sysCredentialOpaqueMapper;
        this.sysCredentialNamespacesMapper = sysCredentialNamespacesMapper;
        this.stringEncryptor = stringEncryptor;
    }
}
