package org.dromara.system.domain;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.vo.SysOssVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:04+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysOssToSysOssVoMapperImpl implements SysOssToSysOssVoMapper {

    @Override
    public SysOssVo convert(SysOss arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysOssVo sysOssVo = new SysOssVo();

        sysOssVo.setCreateBy( arg0.getCreateBy() );
        sysOssVo.setCreateTime( arg0.getCreateTime() );
        sysOssVo.setExt1( arg0.getExt1() );
        sysOssVo.setFileName( arg0.getFileName() );
        sysOssVo.setFileSuffix( arg0.getFileSuffix() );
        sysOssVo.setOriginalName( arg0.getOriginalName() );
        sysOssVo.setOssId( arg0.getOssId() );
        sysOssVo.setService( arg0.getService() );
        sysOssVo.setUrl( arg0.getUrl() );

        return sysOssVo;
    }

    @Override
    public SysOssVo convert(SysOss arg0, SysOssVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setExt1( arg0.getExt1() );
        arg1.setFileName( arg0.getFileName() );
        arg1.setFileSuffix( arg0.getFileSuffix() );
        arg1.setOriginalName( arg0.getOriginalName() );
        arg1.setOssId( arg0.getOssId() );
        arg1.setService( arg0.getService() );
        arg1.setUrl( arg0.getUrl() );

        return arg1;
    }
}
