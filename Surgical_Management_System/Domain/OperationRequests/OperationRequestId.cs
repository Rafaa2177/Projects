using System;
using System.Text.Json.Serialization;
using DDDSample1.Domain.Shared;

namespace DDDSample1.Domain.OperationRequests;

public class OperationRequestId: EntityId
{
    [JsonConstructor]
    public OperationRequestId(Guid value) : base(value)
    {
    }

    public OperationRequestId(string value) : base(value)
    {
    }

    protected override object createFromString(string text)
    {
        return new Guid(text);
    }

    public override string AsString()
    {
        var obj = (Guid) base.ObjValue;
        return obj.ToString();
    }

    public Guid AsGuid()
    {
        return (Guid) base.ObjValue;
    }
    
}