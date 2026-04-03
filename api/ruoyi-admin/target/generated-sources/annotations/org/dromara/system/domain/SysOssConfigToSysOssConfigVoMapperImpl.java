package org.dromara.system.domain;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.vo.SysOssConfigVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:04+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysOssConfigToSysOssConfigVoMapperImpl implements SysOssConfigToSysOssConfigVoMapper {

    @Override
    public SysOssConfigVo convert(SysOssConfig arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysOssConfigVo sysOssConfigVo = new SysOssConfigVo();

        sysOssConfigVo.setAccessKey( arg0.getAccessKey() );
        sysOssConfigVo.setAccessPolicy( arg0.getAccessPolicy() );
        sysOssConfigVo.setBucketName( arg0.getBucketName() );
        sysOssConfigVo.setConfigKey( arg0.getConfigKey() );
        sysOssConfigVo.setDomain( arg0.getDomain() );
        sysOssConfigVo.setEndpoint( arg0.getEndpoint() );
        sysOssConfigVo.setExt1( arg0.getExt1() );
        sysOssConfigVo.setIsHttps( arg0.getIsHttps() );
        sysOssConfigVo.setOssConfigId( arg0.getOssConfigId() );
        sysOssConfigVo.setPrefix( arg0.getPrefix() );
        sysOssConfigVo.setRegion( arg0.getRegion() );
        sysOssConfigVo.setRemark( arg0.getRemark() );
        sysOssConfigVo.setSecretKey( arg0.getSecretKey() );
        sysOssConfigVo.setStatus( arg0.getStatus() );

        return sysOssConfigVo;
    }

    @Override
    public SysOssConfigVo convert(SysOssConfig arg0, SysOssConfigVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setAccessKey( arg0.getAccessKey() );
        arg1.setAccessPolicy( arg0.getAccessPolicy() );
        arg1.setBucketName( arg0.getBucketName() );
        arg1.setConfigKey( arg0.getConfigKey() );
        arg1.setDomain( arg0.getDomain() );
        arg1.setEndpoint( arg0.getEndpoint() );
        arg1.setExt1( arg0.getExt1() );
        arg1.setIsHttps( arg0.getIsHttps() );
        arg1.setOssConfigId( arg0.getOssConfigId() );
        arg1.setPrefix( arg0.getPrefix() );
        arg1.setRegion( arg0.getRegion() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setSecretKey( arg0.getSecretKey() );
        arg1.setStatus( arg0.getStatus() );

        return arg1;
    }
}
