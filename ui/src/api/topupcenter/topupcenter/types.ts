export interface UserBalanceVo {
  /** 用户ID */
  userId: string;

  /** 账户余额 */
  amount: number;

  /** 删除标记 */
  isDelete: number;

  /** 创建时间 */
  createTime: Date;

  /** 更新时间 */
  updateTime: Date;

  /** 创建者 */
  createBy: number;

  /** 更新者 */
  updateBy: number;

  /** 创建部门 */
  createDept: number;
}

export interface UserBalanceBo extends BaseEntity {
  /** 用户ID */
  userId?: string;

  /** 账户余额 */
  amount?: number;

  /** 删除标记 */
  isDelete?: number;
}

export interface UserBalanceQuery extends PageQuery {
  /** 用户ID */
  userId?: string;

  /** 账户余额 */
  amount?: number;

  /** 删除标记 */
  isDelete?: number;

  /** 开始时间 */
  beginTime?: string;

  /** 结束时间 */
  endTime?: string;

  params?: any;
}

/** 充值请求参数 */
export interface RechargeRequest {
  /** 用户ID */
  userId: string;

  /** 充值金额 */
  amount: number;
}

/** 提现请求参数 */
export interface WithdrawRequest {
  /** 用户ID */
  userId: string;

  /** 提现金额 */
  amount: number;
}
