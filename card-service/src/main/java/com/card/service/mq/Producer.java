//package com.card.service.mq;
//
//import com.alibaba.fastjson.JSONObject;
//import com.card.domain.task.Task;
//import com.card.domain.task.TaskMessage;
//import com.card.domain.task.enums.TaskTypeEnum;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.annotation.Validated;
//
//import javax.annotation.Resource;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//import java.util.Map;
//
///**
// * @author yangzhanbang
// * @Email yangzhanbang@jd.com
// * @date 2018/3/6 19:35
// */
//@Service
//public class Producer {
//
//	@Resource(name = "jmsTemplate")
//	private JmsTemplate jmsTemplate;
//
//	@Resource
//	private Map<String, Destination> queueMap;
//
//	public void sendTask(Task task) {
//		TaskMessage message = buildMessage(task);
//		sendMessage(message);
//	}
//
//	/**
//	 * @param taskMessage 向指定的连接队列发送字符串消息
//	 */
//	private void sendMessage(TaskMessage taskMessage) {
//		if (StringUtils.isEmpty(taskMessage.getQueue())) {
//			throw new RuntimeException("消息【" + taskMessage + "】的队列名为空");
//		}
//		if (StringUtils.isEmpty(taskMessage.getTaskString())) {
//			throw new RuntimeException("消息【" + taskMessage + "】的任务为空");
//		}
//		Destination destination = taskMessage.getQueue();
//		String msg = taskMessage.getTaskString();
//		System.out.println(Thread.currentThread().getName() + " 向队列" + taskMessage.getQueue() + "发送消息---------------------->" + msg);
//		jmsTemplate.send(destination, new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createTextMessage(msg);
//			}
//		});
//	}
//
//	private TaskMessage buildMessage(Task task) {
//		TaskMessage taskMessage = new TaskMessage();
//		taskMessage.setTaskString(JSONObject.toJSONString(task));
//		taskMessage.setQueue(queueMap.get(TaskTypeEnum.typeOf(task.getTaskType()).getQueue()));
//		return taskMessage;
//	}
//
//
//}
