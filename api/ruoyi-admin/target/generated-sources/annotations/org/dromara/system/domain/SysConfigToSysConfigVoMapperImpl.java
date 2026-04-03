package org.dromara.system.domain;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.vo.SysConfigVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:04+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysConfigToSysConfigVoMapperImpl implements SysConfigToSysConfigVoMapper {

    @Override
    public SysConfigVo convert(SysConfig arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysConfigVo sysConfigVo = new SysConfigVo();

        sysConfigVo.setConfigId( arg0.getConfigId() );
        sysConfigVo.setConfigKey( arg0.getConfigKey() );
        sysConfigVo.setConfigName( arg0.getConfigName() );
        sysConfigVo.setConfigType( arg0.getConfigType() );
        sysConfigVo.setConfigValue( arg0.getConfigValue() );
        sysConfigVo.setCreateTime( arg0.getCreateTime() );
        sysConfigVo.setRemark( arg0.getRemark() );

        return sysConfigVo;
    }

    @Override
    public SysConfigVo convert(SysConfig arg0, SysConfigVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setConfigId( arg0.getConfigId() );
        arg1.setConfigKey( arg0.getConfigKey() );
        arg1.setConfigName( arg0.getConfigName() );
        arg1.setConfigType( arg0.getConfigType() );
        arg1.setConfigValue( arg0.getConfigValue() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setRemark( arg0.getRemark() );

        return arg1;
    }
}
