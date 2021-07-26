package conversation_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import interfaces.INLPComponent;
import interfaces_implementation.NLPComponent;
import skills.GreetingSkill;

class TimeoutTest {

	private final CountDownLatch waiter = new CountDownLatch(1);
	private INLPComponent nlp = new NLPComponent();
	private Locale defaultLanguage = new Locale("de", "DE");

	@Test
	@DisplayName("TimeoutState correctly reached")
	void correctTimeoutState() throws InterruptedException {
		ConversationEngine ConversationEngine = createNewConversationEngine();
		assertEquals("defaultState", ConversationEngine.getState());
		waiter.await(1200, TimeUnit.MILLISECONDS);
		assertEquals("sleepState", ConversationEngine.getState());
	}

	@Test
	@DisplayName("TimeoutState correctly left")
	void backToDefaultState() throws InterruptedException {
		ConversationEngine ConversationEngine = createNewConversationEngine();
		assertEquals("defaultState", ConversationEngine.getState());
		waiter.await(1200, TimeUnit.MILLISECONDS);
		assertEquals("sleepState", ConversationEngine.getState());
		List<String> answer = ConversationEngine.userInput("Rezept");
		assertEquals("defaultState", ConversationEngine.getState());
		assertEquals("Willkommen zurück!", answer.get(0));
	}

	private ConversationEngine createNewConversationEngine() {
		ConversationEngine ConversationEngine = new ConversationEngine(nlp, 1, defaultLanguage);
		GreetingSkill greet = new GreetingSkill();
		String greetingSkillStateMachine = TestHelperFunctions.loadJsonFileAsString("Greeting.json");
		ConversationEngine.addSkill(greet, greetingSkillStateMachine);
		return ConversationEngine;
	}
}
