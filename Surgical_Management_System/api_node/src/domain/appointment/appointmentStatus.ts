export enum AppointmentStatus {
    SCHEDULED = 'SCHEDULED',
    COMPLETED = 'COMPLETED',
    CANCELLED = 'CANCELLED',
    IN_PROGRESS = 'IN_PROGRESS'
}

export const isValidAppointmentStatus = (status: string): boolean => {
    return Object.values(AppointmentStatus).includes(status.toUpperCase() as AppointmentStatus);
} 