package data;

import java.util.List;
import java.util.Map;

import interfaces.ISkillAnswer;

public class SkillAnswer implements ISkillAnswer {

	private String transitionTrigger;
	private List<String> answers;
	private Map<String, String> openQuestions;
	private boolean skipUserOutput;

	public SkillAnswer(String transitionTrigger, List<String> answers, boolean skipUserOutput) {
		this.transitionTrigger = transitionTrigger;
		this.answers = answers;
		this.skipUserOutput = skipUserOutput;
	}

	public SkillAnswer(String transitionTrigger, Map<String, String> openQuestions) {
		this.transitionTrigger = transitionTrigger;
		this.openQuestions = openQuestions;
	}

	@Override
	public String getTransitionTrigger() {
		return this.transitionTrigger;
	}

	@Override
	public List<String> answers() {
		return this.answers;
	}

	@Override
	public Map<String, String> requiredQuestionsToBeAnswered() {
		return this.openQuestions;
	}

	@Override
	public boolean skipUserOutput() {
		return this.skipUserOutput;
	}

}
