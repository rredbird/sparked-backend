package coda.model.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.dto.TaskDto;
import coda.shared.interfaces.IDto;
import coda.shared.logging.ILogging;

public class Task implements IDto<Task, TaskDto>  {
    @Autowired
    private ILogging log;

    private UUID id;
    private Order order;

    private String status = "new";
    private Classifier classifier;

    public Task(Order order) {
        id = UUID.randomUUID();
        this.order = order;
    }
    public Task() {
        id = UUID.randomUUID();
    }

    public UUID getId() { return id; }
    public Classifier getClassifier() { return this.classifier; }
    public String getStatus() { return this.status; } 

    public void setId(UUID id) { this.id = id; }
    public void setClassifier(Classifier classifier) { this.classifier = classifier; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public Task fromDto(TaskDto dto) {
        this.order = null;
        this.setId(dto.getId());
        this.setStatus(dto.getStatus());
        this.setClassifier(new Classifier().fromDto(dto.getClassifier()));
        return this;
    }

    @Override
    public TaskDto toDto() {
        TaskDto taskDto = new TaskDto();

        taskDto.setClassifier(this.getClassifier().toDto());
        taskDto.setId(this.getId());
        taskDto.setStatus(this.getStatus());

        return taskDto;
    }
}


