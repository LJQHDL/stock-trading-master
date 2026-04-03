package org.dromara.system.domain.vo;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.SysConfig;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:06+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysConfigVoToSysConfigMapperImpl implements SysConfigVoToSysConfigMapper {

    @Override
    public SysConfig convert(SysConfigVo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysConfig sysConfig = new SysConfig();

        sysConfig.setCreateTime( arg0.getCreateTime() );
        sysConfig.setConfigId( arg0.getConfigId() );
        sysConfig.setConfigKey( arg0.getConfigKey() );
        sysConfig.setConfigName( arg0.getConfigName() );
        sysConfig.setConfigType( arg0.getConfigType() );
        sysConfig.setConfigValue( arg0.getConfigValue() );
        sysConfig.setRemark( arg0.getRemark() );

        return sysConfig;
    }

    @Override
    public SysConfig convert(SysConfigVo arg0, SysConfig arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setConfigId( arg0.getConfigId() );
        arg1.setConfigKey( arg0.getConfigKey() );
        arg1.setConfigName( arg0.getConfigName() );
        arg1.setConfigType( arg0.getConfigType() );
        arg1.setConfigValue( arg0.getConfigValue() );
        arg1.setRemark( arg0.getRemark() );

        return arg1;
    }
}
