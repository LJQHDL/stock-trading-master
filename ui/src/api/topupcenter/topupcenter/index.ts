import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { UserBalanceVo, UserBalanceBo, UserBalanceQuery, RechargeRequest, WithdrawRequest } from './types';

/**
 * 分页查询用户余额列表
 * @param query
 * @returns {*}
 */
export const listUserBalance = (query?: UserBalanceQuery): AxiosPromise<UserBalanceVo[]> => {
  return request({
    url: '/userBalance/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询用户余额列表
 * @param query
 * @returns {*}
 */
export const listAllUserBalance = (query?: UserBalanceBo): AxiosPromise<UserBalanceVo[]> => {
  return request({
    url: '/userBalance/listAll',
    method: 'get',
    params: query
  });
};

/**
 * 根据用户ID获取用户余额详细信息
 * @param userId
 */
export const getUserBalance = (userId: string): AxiosPromise<UserBalanceVo> => {
  return request({
    url: '/userBalance/' + userId,
    method: 'get'
  });
};

/**
 * 新增用户余额
 * @param data
 */
export const addUserBalance = (data: UserBalanceBo) => {
  return request({
    url: '/userBalance',
    method: 'post',
    data: data
  });
};

/**
 * 修改用户余额
 * @param data
 */
export const updateUserBalance = (data: UserBalanceBo) => {
  return request({
    url: '/userBalance',
    method: 'put',
    data: data
  });
};

/**
 * 删除用户余额
 * @param userIds
 */
export const delUserBalance = (userIds: string | Array<string>) => {
  return request({
    url: '/userBalance/' + userIds,
    method: 'delete'
  });
};

/**
 * 增加用户余额
 * @param userId
 * @param amount
 */
export const addBalance = (userId: string, amount: number) => {
  return request({
    url: `/userBalance/add/${userId}/${amount}`,
    method: 'post'
  });
};

/**
 * 减少用户余额
 * @param userId
 * @param amount
 */
export const subtractBalance = (userId: string, amount: number) => {
  return request({
    url: `/userBalance/subtract/${userId}/${amount}`,
    method: 'post'
  });
};

/**
 * 充值接口
 * @param data
 */
export const recharge = (data: RechargeRequest) => {
  return request({
    url: '/userBalance/recharge',
    method: 'post',
    data: data
  });
};

/**
 * 提现接口
 * @param data
 */
export const withdraw = (data: WithdrawRequest) => {
  return request({
    url: '/userBalance/withdraw',
    method: 'post',
    data: data
  });
};
