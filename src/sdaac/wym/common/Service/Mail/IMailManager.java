package sdaac.wym.common.Service.Mail;

import java.util.List;
import common.entity.MyMail;
/**
 * business Mail Interface
 * @author SA1KV5
 *
 */
public interface IMailManager {
	//get mails that waiting for being sent
	public List<MyMail> doSelectToMailTip();
}
