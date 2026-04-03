export interface StockVO {
  /**
   * ID
   */
  id: string | number;

  /**
   * 股票名字
   */
  name: string;

  /**
   * 代码
   */
  code: string;

  /**
   * 备注
   */
  remark: string;
}

export interface StockForm extends BaseEntity {
  /**
   * ID
   */
  id?: string | number;

  /**
   * 股票名字
   */
  name?: string;

  /**
   * 代码
   */
  code?: string;

  /**
   * 备注
   */
  remark?: string;
}

export interface StockQuery extends PageQuery {
  /**
   * 股票名字
   */
  name?: string;

  /**
   * 代码
   */
  code?: string;

  /**
   * 日期范围参数
   */
  params?: any;
}
