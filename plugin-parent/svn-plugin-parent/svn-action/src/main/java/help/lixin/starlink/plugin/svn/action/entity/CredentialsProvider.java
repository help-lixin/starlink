package help.lixin.starlink.plugin.svn.action.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    value = {@JsonSubTypes.Type(value = UsernamePasswordCredentialsProvider.class, name = "username-password")})
public interface CredentialsProvider {

}
