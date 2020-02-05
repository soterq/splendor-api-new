package splendorapi.api;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import splendorapi.api.viewmodel.FeedbackViewModel;
import splendorapi.mail.FeedbackSender;

import javax.xml.bind.ValidationException;

@Controller
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeetBackController {
    private final FeedbackSender feedbackSender;

    public FeetBackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }

    @PostMapping
    public void sendFeedBack(@RequestBody FeedbackViewModel feedBackModel,
                             BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("FeedBack has errors, can not send feedback");
        }
        this.feedbackSender.sendFeedback(
                feedBackModel.getEmail(),
                feedBackModel.getName(),
                feedBackModel.getFeedback()
        );

    }
}
