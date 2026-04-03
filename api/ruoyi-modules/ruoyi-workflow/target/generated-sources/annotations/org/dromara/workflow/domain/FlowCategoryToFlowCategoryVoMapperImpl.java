package org.dromara.workflow.domain;

import java.util.List;
import javax.annotation.processing.Generated;
import org.dromara.workflow.domain.vo.FlowCategoryVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:46+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class FlowCategoryToFlowCategoryVoMapperImpl implements FlowCategoryToFlowCategoryVoMapper {

    @Override
    public FlowCategoryVo convert(FlowCategory arg0) {
        if ( arg0 == null ) {
            return null;
        }

        FlowCategoryVo flowCategoryVo = new FlowCategoryVo();

        flowCategoryVo.setAncestors( arg0.getAncestors() );
        flowCategoryVo.setCategoryId( arg0.getCategoryId() );
        flowCategoryVo.setCategoryName( arg0.getCategoryName() );
        flowCategoryVo.setChildren( convert( arg0.getChildren() ) );
        flowCategoryVo.setCreateTime( arg0.getCreateTime() );
        flowCategoryVo.setOrderNum( arg0.getOrderNum() );
        flowCategoryVo.setParentId( arg0.getParentId() );

        return flowCategoryVo;
    }

    @Override
    public FlowCategoryVo convert(FlowCategory arg0, FlowCategoryVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setAncestors( arg0.getAncestors() );
        arg1.setCategoryId( arg0.getCategoryId() );
        arg1.setCategoryName( arg0.getCategoryName() );
        if ( arg1.getChildren() != null ) {
            List<FlowCategoryVo> list = convert( arg0.getChildren() );
            if ( list != null ) {
                arg1.getChildren().clear();
                arg1.getChildren().addAll( list );
            }
            else {
                arg1.setChildren( null );
            }
        }
        else {
            List<FlowCategoryVo> list = convert( arg0.getChildren() );
            if ( list != null ) {
                arg1.setChildren( list );
            }
        }
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setOrderNum( arg0.getOrderNum() );
        arg1.setParentId( arg0.getParentId() );

        return arg1;
    }
}
