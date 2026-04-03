package org.dromara.system.domain.bo;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.dromara.common.log.event.OperLogEvent;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:01+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysOperLogBoToOperLogEventMapperImpl implements SysOperLogBoToOperLogEventMapper {

    @Override
    public OperLogEvent convert(SysOperLogBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        OperLogEvent operLogEvent = new OperLogEvent();

        operLogEvent.setBusinessType( arg0.getBusinessType() );
        Integer[] businessTypes = arg0.getBusinessTypes();
        if ( businessTypes != null ) {
            operLogEvent.setBusinessTypes( Arrays.copyOf( businessTypes, businessTypes.length ) );
        }
        operLogEvent.setCostTime( arg0.getCostTime() );
        operLogEvent.setDeptName( arg0.getDeptName() );
        operLogEvent.setErrorMsg( arg0.getErrorMsg() );
        operLogEvent.setJsonResult( arg0.getJsonResult() );
        operLogEvent.setMethod( arg0.getMethod() );
        operLogEvent.setOperId( arg0.getOperId() );
        operLogEvent.setOperIp( arg0.getOperIp() );
        operLogEvent.setOperLocation( arg0.getOperLocation() );
        operLogEvent.setOperName( arg0.getOperName() );
        operLogEvent.setOperParam( arg0.getOperParam() );
        operLogEvent.setOperTime( arg0.getOperTime() );
        operLogEvent.setOperUrl( arg0.getOperUrl() );
        operLogEvent.setOperatorType( arg0.getOperatorType() );
        operLogEvent.setRequestMethod( arg0.getRequestMethod() );
        operLogEvent.setStatus( arg0.getStatus() );
        operLogEvent.setTenantId( arg0.getTenantId() );
        operLogEvent.setTitle( arg0.getTitle() );

        return operLogEvent;
    }

    @Override
    public OperLogEvent convert(SysOperLogBo arg0, OperLogEvent arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setBusinessType( arg0.getBusinessType() );
        Integer[] businessTypes = arg0.getBusinessTypes();
        if ( businessTypes != null ) {
            arg1.setBusinessTypes( Arrays.copyOf( businessTypes, businessTypes.length ) );
        }
        else {
            arg1.setBusinessTypes( null );
        }
        arg1.setCostTime( arg0.getCostTime() );
        arg1.setDeptName( arg0.getDeptName() );
        arg1.setErrorMsg( arg0.getErrorMsg() );
        arg1.setJsonResult( arg0.getJsonResult() );
        arg1.setMethod( arg0.getMethod() );
        arg1.setOperId( arg0.getOperId() );
        arg1.setOperIp( arg0.getOperIp() );
        arg1.setOperLocation( arg0.getOperLocation() );
        arg1.setOperName( arg0.getOperName() );
        arg1.setOperParam( arg0.getOperParam() );
        arg1.setOperTime( arg0.getOperTime() );
        arg1.setOperUrl( arg0.getOperUrl() );
        arg1.setOperatorType( arg0.getOperatorType() );
        arg1.setRequestMethod( arg0.getRequestMethod() );
        arg1.setStatus( arg0.getStatus() );
        arg1.setTenantId( arg0.getTenantId() );
        arg1.setTitle( arg0.getTitle() );

        return arg1;
    }
}
