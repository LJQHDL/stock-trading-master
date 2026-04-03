package org.dromara.system.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.system.domain.SysOss;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:05+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysOssBoToSysOssMapperImpl implements SysOssBoToSysOssMapper {

    @Override
    public SysOss convert(SysOssBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysOss sysOss = new SysOss();

        sysOss.setCreateBy( arg0.getCreateBy() );
        sysOss.setCreateDept( arg0.getCreateDept() );
        sysOss.setCreateTime( arg0.getCreateTime() );
        Map<String, Object> map = arg0.getParams();
        if ( map != null ) {
            sysOss.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        sysOss.setSearchValue( arg0.getSearchValue() );
        sysOss.setUpdateBy( arg0.getUpdateBy() );
        sysOss.setUpdateTime( arg0.getUpdateTime() );
        sysOss.setExt1( arg0.getExt1() );
        sysOss.setFileName( arg0.getFileName() );
        sysOss.setFileSuffix( arg0.getFileSuffix() );
        sysOss.setOriginalName( arg0.getOriginalName() );
        sysOss.setOssId( arg0.getOssId() );
        sysOss.setService( arg0.getService() );
        sysOss.setUrl( arg0.getUrl() );

        return sysOss;
    }

    @Override
    public SysOss convert(SysOssBo arg0, SysOss arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateDept( arg0.getCreateDept() );
        arg1.setCreateTime( arg0.getCreateTime() );
        if ( arg1.getParams() != null ) {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.getParams().clear();
                arg1.getParams().putAll( map );
            }
            else {
                arg1.setParams( null );
            }
        }
        else {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.setParams( new LinkedHashMap<String, Object>( map ) );
            }
        }
        arg1.setSearchValue( arg0.getSearchValue() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
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
