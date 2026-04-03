package org.dromara.system.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.system.domain.SysTenantPackage;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:33+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SysTenantPackageBoToSysTenantPackageMapperImpl implements SysTenantPackageBoToSysTenantPackageMapper {

    @Override
    public SysTenantPackage convert(SysTenantPackageBo source) {
        if ( source == null ) {
            return null;
        }

        SysTenantPackage sysTenantPackage = new SysTenantPackage();

        sysTenantPackage.setSearchValue( source.getSearchValue() );
        sysTenantPackage.setCreateDept( source.getCreateDept() );
        sysTenantPackage.setCreateBy( source.getCreateBy() );
        sysTenantPackage.setCreateTime( source.getCreateTime() );
        sysTenantPackage.setUpdateBy( source.getUpdateBy() );
        sysTenantPackage.setUpdateTime( source.getUpdateTime() );
        Map<String, Object> map = source.getParams();
        if ( map != null ) {
            sysTenantPackage.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        sysTenantPackage.setMenuCheckStrictly( source.getMenuCheckStrictly() );
        sysTenantPackage.setPackageId( source.getPackageId() );
        sysTenantPackage.setPackageName( source.getPackageName() );
        sysTenantPackage.setRemark( source.getRemark() );
        sysTenantPackage.setStatus( source.getStatus() );

        sysTenantPackage.setMenuIds( org.dromara.common.core.utils.StringUtils.join(source.getMenuIds(), ",") );

        return sysTenantPackage;
    }

    @Override
    public SysTenantPackage convert(SysTenantPackageBo source, SysTenantPackage target) {
        if ( source == null ) {
            return target;
        }

        target.setSearchValue( source.getSearchValue() );
        target.setCreateDept( source.getCreateDept() );
        target.setCreateBy( source.getCreateBy() );
        target.setCreateTime( source.getCreateTime() );
        target.setUpdateBy( source.getUpdateBy() );
        target.setUpdateTime( source.getUpdateTime() );
        if ( target.getParams() != null ) {
            Map<String, Object> map = source.getParams();
            if ( map != null ) {
                target.getParams().clear();
                target.getParams().putAll( map );
            }
            else {
                target.setParams( null );
            }
        }
        else {
            Map<String, Object> map = source.getParams();
            if ( map != null ) {
                target.setParams( new LinkedHashMap<String, Object>( map ) );
            }
        }
        target.setMenuCheckStrictly( source.getMenuCheckStrictly() );
        target.setPackageId( source.getPackageId() );
        target.setPackageName( source.getPackageName() );
        target.setRemark( source.getRemark() );
        target.setStatus( source.getStatus() );

        target.setMenuIds( org.dromara.common.core.utils.StringUtils.join(source.getMenuIds(), ",") );

        return target;
    }
}
