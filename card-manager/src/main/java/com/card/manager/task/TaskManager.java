package com.card.manager.task;



public interface TaskManager{

    /**
     *
     * @param taskId
     * @param oldTaskStatus
     * @param newTaskStatus
     * @param orderId
     * @param oldOrderStatus
     * @param newOrderStatus
     */
    void updateTaskAndOrder(Long taskId,Integer oldTaskStatus,Integer newTaskStatus,Long orderId,Integer oldOrderStatus,Integer newOrderStatus);

}
