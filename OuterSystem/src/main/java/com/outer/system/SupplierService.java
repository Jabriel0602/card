package com.outer.system;

/**
 * @author yangzhanbang
 * @date 2018/4/16 13:08
 * @desc
 */
public interface SupplierService {
	Boolean createOrder() throws InterruptedException;

	Boolean rechargeOrder() throws InterruptedException;

	Boolean refundeOrder() throws InterruptedException;

}
