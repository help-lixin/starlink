/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package help.lixin.starlink.plugin.nacos.api.model.enums;

/**
 * @Param null :
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:20 下午
 * @Description
*/
public enum NacosImportEnum {
    
    /**
     * 中止重复导入
     */
    ABORT,
    
    /**
     * 跳过重复项
     */
    SKIP,
    
    /**
     * 覆盖重复项
     */
    OVERWRITE
    
}
