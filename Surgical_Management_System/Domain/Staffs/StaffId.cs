using System;
using System.Text.Json.Serialization;
using DDDSample1.Domain.Shared;

namespace DDDSample1.Domain.Staff
{
    public class StaffId : EntityId
    {
        [JsonConstructor]
        public StaffId(Guid value) : base(value)
        {
        }

        public StaffId(string value) : base(value) 
        {
        }

        protected override object createFromString(string text)
        {
            return new Guid(text); 
        }

        override
        
            public String AsString()
        {
            Guid obj = (Guid)base.ObjValue;
            return obj.ToString();
        }

        public Guid AsGuid() 
        {
            return (Guid)base.ObjValue;
        }
    }
}