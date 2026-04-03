package org.dromara.web.controller;

import java.util.List;

import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.web.domain.vo.StockVo;
import org.dromara.web.domain.bo.StockBo;
import org.dromara.web.service.IStockService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 股票信息
 *
 * @author Yao Bo
 * @date 2025-03-22
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/stock/stock")
public class StockController extends BaseController {

    private final IStockService stockService;

    /**
     * 查询股票信息列表
     */
   // @SaCheckPermission("stock:stock:list")
    @SaIgnore
    @GetMapping("/list")
    public TableDataInfo<StockVo> list(StockBo bo, PageQuery pageQuery) {
        return stockService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出股票信息列表
     */
  //  @SaCheckPermission("stock:stock:export")
    @SaIgnore
    @Log(title = "股票信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StockBo bo, HttpServletResponse response) {
        List<StockVo> list = stockService.queryList(bo);
        ExcelUtil.exportExcel(list, "股票信息", StockVo.class, response);
    }

    /**
     * 获取股票信息详细信息
     *
     * @param id 主键
     */
   // @SaCheckPermission("stock:stock:query")
    @SaIgnore
    @GetMapping("/{id}")
    public R<StockVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(stockService.queryById(id));
    }

    /**
     * 新增股票信息
     */
  //  @SaCheckPermission("stock:stock:add")
    @SaIgnore
    @Log(title = "股票信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StockBo bo) {
        return toAjax(stockService.insertByBo(bo));
    }

    /**
     * 修改股票信息
     */
   //  @SaCheckPermission("stock:stock:edit")
    @SaIgnore
    @Log(title = "股票信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StockBo bo) {
        return toAjax(stockService.updateByBo(bo));
    }

    /**
     * 删除股票信息
     *
     * @param ids 主键串
     */
   // @SaCheckPermission("stock:stock:remove")
    @SaIgnore
    @Log(title = "股票信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(stockService.deleteWithValidByIds(List.of(ids), true));
    }
}
