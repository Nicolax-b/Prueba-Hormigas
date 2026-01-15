package BusinessLogic.Entities;

import BusinessLogic.Interface.IIngestaNativa;

public abstract class IngestaNativa implements IIngestaNativa{
    protected String tipo;

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
