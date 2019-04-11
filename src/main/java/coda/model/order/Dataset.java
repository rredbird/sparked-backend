package coda.model.order;

import coda.shared.dto.DatasetDto;
import coda.shared.interfaces.IDto;

public class Dataset implements IDto<Dataset, DatasetDto>
{
    private String id;
    private String revision;
    private String description;

    public Dataset() {
        id = "";
        revision = "";
        description = "";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRevision() { return revision; }
    public void setRevision(String revision) { this.revision = revision; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public Dataset fromDto(DatasetDto dto) {
        this.setId(dto.getId());
        this.setDescription(dto.getDescription());
        this.setRevision(dto.getRevision());

        return this;
    }

    @Override
    public DatasetDto toDto() {
        DatasetDto retVal = new DatasetDto();

        retVal.setId(this.id);
        retVal.setDescription(this.description);
        retVal.setRevision(this.revision);

        return retVal;
    }
}


