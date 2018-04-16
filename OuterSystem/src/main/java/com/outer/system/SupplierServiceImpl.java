package com.outer.system;

import org.springframework.stereotype.Service;

/**
 * @author yangzhanbang
 * @date 2018/4/16 13:08
 * @desc
 */
@Service
public class SupplierServiceImpl implements SupplierService{
	@Override
	public Boolean createOrder() throws InterruptedException {
		Thread.sleep(2000);
		return true;
	}

	@Override
	public Boolean rechargeOrder() throws InterruptedException {
		Thread.sleep(2000);
		return true;
	}
}
