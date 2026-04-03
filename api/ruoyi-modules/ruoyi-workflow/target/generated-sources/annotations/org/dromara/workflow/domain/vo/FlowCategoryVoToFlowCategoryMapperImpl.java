package org.dromara.workflow.domain.vo;

import java.util.List;
import javax.annotation.processing.Generated;
import org.dromara.workflow.domain.FlowCategory;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:45+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class FlowCategoryVoToFlowCategoryMapperImpl implements FlowCategoryVoToFlowCategoryMapper {

    @Override
    public FlowCategory convert(FlowCategoryVo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        FlowCategory flowCategory = new FlowCategory();

        flowCategory.setCreateTime( arg0.getCreateTime() );
        flowCategory.setAncestors( arg0.getAncestors() );
        flowCategory.setCategoryId( arg0.getCategoryId() );
        flowCategory.setCategoryName( arg0.getCategoryName() );
        flowCategory.setChildren( convert( arg0.getChildren() ) );
        flowCategory.setOrderNum( arg0.getOrderNum() );
        flowCategory.setParentId( arg0.getParentId() );

        return flowCategory;
    }

    @Override
    public FlowCategory convert(FlowCategoryVo arg0, FlowCategory arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setAncestors( arg0.getAncestors() );
        arg1.setCategoryId( arg0.getCategoryId() );
        arg1.setCategoryName( arg0.getCategoryName() );
        if ( arg1.getChildren() != null ) {
            List<FlowCategory> list = convert( arg0.getChildren() );
            if ( list != null ) {
                arg1.getChildren().clear();
                arg1.getChildren().addAll( list );
            }
            else {
                arg1.setChildren( null );
            }
        }
        else {
            List<FlowCategory> list = convert( arg0.getChildren() );
            if ( list != null ) {
                arg1.setChildren( list );
            }
        }
        arg1.setOrderNum( arg0.getOrderNum() );
        arg1.setParentId( arg0.getParentId() );

        return arg1;
    }
}
