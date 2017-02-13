package com.u2u.framework.jms;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.u2u.framework.exception.ServiceMailException;
import com.u2u.framework.jms.mail.Mail;
import com.u2u.framework.jms.mail.MailManager;
import com.u2u.framework.jms.mail.MailTemplateKey;
import com.u2u.framework.jms.mail.impl.MailManagerImpl;

/**
 * @ClassName: MessageManager
 * @Description: 鍙戦�佹秷鎭伐鍏风被 <br>
 * @date 2015-3-17 涓嬪崍3:18:51 <br>
 * 
 * @author Freud
 */
public class MessageManager {
	private MailManager mailManager = new MailManagerImpl();

	private static MessageManager messageManager;

	private static synchronized MessageManager getMailManagerInstance() {
		if (messageManager == null) {
			messageManager = new MessageManager();
		}
		return messageManager;
	}

	/**
	 * <p>
	 * Discription:[鏂规硶鍔熻兘涓枃鎻忚堪]
	 * </p>
	 * 
	 * @return MailManager mailManager.
	 */
	private MailManager getMailManager() {
		return mailManager;
	}

	/**
	 * 
	 * <p>
	 * Discription:[鍙戦�侀偖浠禲
	 * </p>
	 * 
	 * @param sendTo
	 *            鏀朵欢浜�
	 * @param content
	 *            鍐呭鏇挎崲鍒楄〃
	 * @param template
	 *            妯＄増
	 * @param isHtmlBody
	 *            鏄惁HTML绫诲瀷鐨勯鏂囨。鍐呭
	 * @throws ServiceMailException
	 * @author:[Freud]
	 * @update:[2015-3-17] [Freud][鍙樻洿鎻忚堪]
	 */
	public static void sendMail(String sendTo, Map<String, String> content, MailTemplateKey template)
			throws ServiceMailException {
		MailManager mailManagerInMethod = MessageManager.getMailManagerInstance().getMailManager();
		Mail mail = mailManagerInMethod.newMailInstance(true);
		mail.addTo(sendTo);

		for (Entry<String, String> entry : content.entrySet()) {
			mail.addContent(entry.getKey(), entry.getValue());
		}
		mailManagerInMethod.sendMail(mail, template);
	}

	public static void sendMailAndFile(String sendTo, Map<String, String> content, MailTemplateKey template,
			Map<String, File> files) throws ServiceMailException {
		MailManager mailManagerInMethod = MessageManager.getMailManagerInstance().getMailManager();
		Mail mail = mailManagerInMethod.newMailInstance(true);
		mail.addTo(sendTo);

		for (Entry<String, String> entry : content.entrySet()) {
			mail.addContent(entry.getKey(), entry.getValue());
		}
		for (Entry<String, File> contractFile : files.entrySet()) {
			mail.addAttache(contractFile.getValue().getAbsolutePath(), contractFile.getKey() + ".pdf", "Attached File");

		}
		mailManagerInMethod.sendMail(mail, template);
	}
}
