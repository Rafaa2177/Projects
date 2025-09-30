
using System;
using Microsoft.EntityFrameworkCore;

namespace DDDSample1.Domain.Staff
{
    
    public class TimeSlots
    {
        
        public DateTime StartTime { get; private set; }
        public DateTime EndTime { get; private set; }

        public TimeSlots(DateTime startTime, DateTime endTime)
        {
            if (endTime <= startTime)
            {
                throw new ArgumentException("EndTime must be after StartTime");
            }

            this.StartTime = startTime;
            this.EndTime = endTime;
        }

        public void ChangeTimeSlot(DateTime startTime, DateTime endTime)
        {
            if (endTime <= startTime)
            {
                throw new ArgumentException("EndTime must be after StartTime");
            }

            this.StartTime = startTime;
            this.EndTime = endTime;
        }

        public override string ToString()
        {
            return $"{StartTime: yyyy-MM-dd:HH'h'mm} - {EndTime:yyyy-MM-dd:HH'h'mm}";
        }
        
    }
}