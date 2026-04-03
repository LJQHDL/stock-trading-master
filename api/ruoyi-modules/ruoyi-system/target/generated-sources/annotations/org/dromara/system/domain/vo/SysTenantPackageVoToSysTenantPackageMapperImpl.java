package org.dromara.system.domain.vo;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.SysTenantPackage;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:00+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysTenantPackageVoToSysTenantPackageMapperImpl implements SysTenantPackageVoToSysTenantPackageMapper {

    @Override
    public SysTenantPackage convert(SysTenantPackageVo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysTenantPackage sysTenantPackage = new SysTenantPackage();

        sysTenantPackage.setMenuCheckStrictly( arg0.getMenuCheckStrictly() );
        sysTenantPackage.setMenuIds( arg0.getMenuIds() );
        sysTenantPackage.setPackageId( arg0.getPackageId() );
        sysTenantPackage.setPackageName( arg0.getPackageName() );
        sysTenantPackage.setRemark( arg0.getRemark() );
        sysTenantPackage.setStatus( arg0.getStatus() );

        return sysTenantPackage;
    }

    @Override
    public SysTenantPackage convert(SysTenantPackageVo arg0, SysTenantPackage arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setMenuCheckStrictly( arg0.getMenuCheckStrictly() );
        arg1.setMenuIds( arg0.getMenuIds() );
        arg1.setPackageId( arg0.getPackageId() );
        arg1.setPackageName( arg0.getPackageName() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setStatus( arg0.getStatus() );

        return arg1;
    }
}
