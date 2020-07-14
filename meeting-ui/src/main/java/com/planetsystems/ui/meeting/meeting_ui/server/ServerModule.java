package com.planetsystems.ui.meeting.meeting_ui.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.server.spring.HandlerModule;
import com.gwtplatform.dispatch.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.server.spring.configuration.DefaultModule;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.MeetingsAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.MinutesAction;
import com.planetsystems.ui.utils.utils_ui.server.UtilActionHandler;
import com.planetsystems.ui.utils.utils_ui.shared.Util;

@Configuration
@Import(DefaultModule.class)
public class ServerModule extends HandlerModule {
	// Required
	public ServerModule() {

	}

	// Required
	@Bean
	public ActionValidator getDefaultActionValidator() {
		return new DefaultActionValidator();
	}

	@Override
	protected void configureHandlers() {

		bindHandler(MeetingsAction.class, MeetingsActionHandler.class);
		
		bindHandler(CommitteeAction.class,CommitteeActionActionHandler.class);
		bindHandler(AgendaItemAction.class,AgendaActionHandler.class);
		bindHandler(MinutesAction.class,MinutesActionHandler.class);

		bindHandler(Util.class, UtilActionHandler.class);
	}

	@Bean
	public MeetingsActionHandler getBudgetActionActionHandler() {
		return new MeetingsActionHandler();
	}
	
	@Bean
	public CommitteeActionActionHandler getCommitteeActionActionHandler() {
		return new CommitteeActionActionHandler();
	}
	
	@Bean
	public AgendaActionHandler getAgendaActionHandler() {
		return new AgendaActionHandler();
	}
	
	@Bean
	public MinutesActionHandler getMinutesActionHandler() {
		return new MinutesActionHandler();
	}

	/* utils integration */
	@Bean
	public UtilActionHandler getUtilActionHandler() {
		return new UtilActionHandler();
	}
}
