export interface StockUsaVo {
  /** 主键ID */
  id: number;

  /** 公司名称 */
  cname: string;

  /** 行业板块 */
  category: string;

  /** 股票代码 */
  symbol: string;

  /** 最新价 */
  price: number;

  /** 涨跌额 */
  diff: number;

  /** 涨跌幅(%) */
  chg: number;

  /** 昨收价 */
  preclose: number;

  /** 今开价 */
  open: number;

  /** 最高价 */
  high: number;

  /** 最低价 */
  low: number;

  /** 振幅(%) */
  amplitude: number;

  /** 成交量 */
  volume: number;

  /** 市值 */
  mktcap: number;

  /** 上市地 */
  market: string;

  /** 更新时间 */
  updateTime: Date;

  /** 创建部门 */
  createDept: number;

  /** 创建者 */
  createBy: number;

  /** 创建时间 */
  createTime: Date;

  /** 更新者 */
  updateBy: number;

  /** 逻辑删除标志（默认0） */
  isDelete: number;
  /**当前日期 */
  currentLdate: Date;
}

export interface StockUsaForm extends BaseEntity {
  /** 主键ID */
  id: number;

  /** 公司名称 */
  cname: string;

  /** 行业板块 */
  category: string;

  /** 股票代码 */
  symbol: string;

  /** 最新价 */
  price: number;

  /** 涨跌额 */
  diff: number;

  /** 涨跌幅(%) */
  chg: number;

  /** 昨收价 */
  preclose: number;

  /** 今开价 */
  open: number;

  /** 最高价 */
  high: number;

  /** 最低价 */
  low: number;

  /** 振幅(%) */
  amplitude: number;

  /** 成交量 */
  volume: number;

  /** 市值 */
  mktcap: number;

  /** 上市地 */
  market: string;

  /** 更新时间 */
  updateTime: any;

  /** 创建部门 */
  createDept: any;

  /** 创建者 */
  createBy: any;

  /** 创建时间 */
  createTime: string;

  /** 更新者 */
  updateBy: any;

  /** 逻辑删除标志（默认0） */
  isDelete: number;

  /**当前日期 */
  currentLdate: Date;
}
export interface StockUsaQuery extends PageQuery {
  /** 主键ID */
  id: number;

  /** 公司名称 */
  cname: string;

  /** 行业板块 */
  category: string;

  /** 股票代码 */
  symbol: string;

  /** 最新价 */
  price: number;

  /** 涨跌额 */
  diff: number;

  /** 涨跌幅(%) */
  chg: number;

  /** 昨收价 */
  preclose: number;

  /** 今开价 */
  open: number;

  /** 最高价 */
  high: number;

  /** 最低价 */
  low: number;

  /** 振幅(%) */
  amplitude: number;

  /** 成交量 */
  volume: number;

  /** 市值 */
  mktcap: number;

  /** 上市地 */
  market: string;
  /** 逻辑删除标志（默认0） */
  isDelete: number;

  currentLdate: Date;
  params?: any;
}
