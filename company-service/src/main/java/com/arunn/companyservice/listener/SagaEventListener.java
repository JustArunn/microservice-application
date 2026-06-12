package com.arunn.companyservice.listener;

import com.arunn.companyservice.event.CompanyCreationFailedEvent;
import com.arunn.companyservice.saga.SagaOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SagaEventListener {

    private final SagaOrchestrator sagaOrchestrator;

    /**
     * Listens for company compensation events when other services fail
     */
    @RabbitListener(queues = "company.compensation.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleCompanyCompensation(CompanyCreationFailedEvent failedEvent) {
        log.info("Received CompanyCreationFailedEvent with correlationId: {} from service: {}",
                failedEvent.getCorrelationId(), failedEvent.getFailedService());
        
        sagaOrchestrator.handleCompensation(failedEvent);
    }
}
