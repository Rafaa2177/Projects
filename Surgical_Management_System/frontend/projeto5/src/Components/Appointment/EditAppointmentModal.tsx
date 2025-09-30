import React, { useState, useEffect } from 'react';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
interface Appointment {
    id: string;
    doctorId: string;
    pacientId: string;
    patientName: string;
    doctorName: string;
    room: string;
    operationType: string;
    startDate: string;
    endDate: string;
    status: string;
}

interface EditAppointmentModalProps {
    appointment: Appointment | null;
    isOpen: boolean;
    onClose: () => void;
    onSave: (updatedAppointment: Appointment) => void;
}

const EditAppointmentModal: React.FC<EditAppointmentModalProps> = ({ appointment, isOpen, onClose, onSave }) => {
    const [formData, setFormData] = useState<Appointment | null>(null);
    const [startDate, setStartDate] = useState<Date | null>(null);
    const [endDate, setEndDate] = useState<Date | null>(null);
    const [isAnimating, setIsAnimating] = useState(false);

    useEffect(() => {
        if (appointment) {
            setFormData({ ...appointment });
            setStartDate(new Date(appointment.startDate));
            setEndDate(new Date(appointment.endDate));
        }
    }, [appointment]);

    useEffect(() => {
        if (isOpen) {
            setIsAnimating(true);
        }
    }, [isOpen]);

    const handleClose = () => {
        setIsAnimating(false);
        setTimeout(onClose, 300);
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        if (formData) {
            const { name, value } = e.target;
            setFormData(prev => prev ? { ...prev, [name]: value } : null);
        }
    };

    const handleSave = () => {
        if (formData) {
            const updatedStartDate = startDate ? startDate.toISOString() : formData.startDate;
            const updatedEndDate = endDate ? endDate.toISOString() : formData.endDate;
            const updatedAppointment = { ...formData, startDate: updatedStartDate, endDate: updatedEndDate };
            onSave(updatedAppointment);
            handleClose();
        }
    };

    if (!isOpen && !isAnimating) return null;

    return (
        <div
            className={`fixed inset-0 z-50 flex items-center justify-center ${
                isAnimating && isOpen
                    ? 'opacity-100 transition-opacity duration-300'
                    : 'opacity-0 transition-opacity duration-300'
            }`}
        >
            <div
                className={`absolute inset-0 bg-black ${
                    isAnimating && isOpen
                        ? 'bg-opacity-50 transition-all duration-300'
                        : 'bg-opacity-0 transition-all duration-300'
                }`}
                onClick={handleClose}
            />

            <div
                className={`relative bg-white rounded-lg shadow-xl w-full max-w-md mx-4 transform ${
                    isAnimating && isOpen
                        ? 'translate-y-0 opacity-100 scale-100 transition-all duration-300'
                        : 'translate-y-4 opacity-0 scale-95 transition-all duration-300'
                }`}
            >
                <div className="p-6">
                    <h2 className="text-xl font-bold mb-4">Edit Appointment</h2>
                    {formData && (
                        <>
                            <div className="space-y-4">
                                <div>
                                    <label htmlFor="doctorId" className="block text-sm font-medium text-gray-700 mb-2">Doctor ID</label>
                                    <input
                                        type="text"
                                        id="doctorId"
                                        name="doctorId"
                                        value={formData.doctorId}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                        disabled={true}
                                    />
                                </div>
                                <div>
                                    <label htmlFor="pacientId" className="block text-sm font-medium text-gray-700 mb-2">Patient ID</label>
                                    <input
                                        type="text"
                                        id="pacientId"
                                        name="pacientId"
                                        value={formData.pacientId}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                        disabled={true}
                                    />
                                </div>
                                <div>
                                    <label htmlFor="operationType" className="block text-sm font-medium text-gray-700 mb-2">Operation Type</label>
                                    <select
                                        name="operationType"
                                        value={formData.operationType}
                                        onChange={handleChange}
                                        defaultValue={"Cardiology"}
                                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                                        required
                                    >
                                        <option value="Cardiology">Cardiology</option>
                                        <option value="Neurology">Neurology</option>
                                        <option value="Orthopedics">Orthopedics</option>
                                        <option value="Gastroenterology">Gastroenterology</option>
                                        <option value="Pulmonology">Pulmonology</option>
                                        <option value="Oncology">Oncology</option>
                                        <option value="Endocrinology">Endocrinology</option>
                                        <option value="Pediatrics">Pediatrics</option>
                                        <option value="Dermatology">Dermatology</option>
                                        <option value="Urology">Urology</option>
                                        <option value="Gynecology/Obstetrics">Gynecology/Obstetrics</option>
                                        <option value="Ophthalmology">Ophthalmology</option>
                                        <option value="Psychiatry">Psychiatry</option>
                                        <option value="General Surgery">General Surgery</option>
                                        <option value="Radiology">Radiology</option>
                                        <option value="Emergency Medicine">Emergency Medicine</option>
                                    </select>
                                </div>
                                <div>
                                    <label htmlFor="startDate" className="block text-sm font-medium text-gray-700 mb-2">Start
                                        Date</label>
                                    <DatePicker
                                        selected={startDate}
                                        onChange={(startdate: Date) => setStartDate(startdate)}
                                        showTimeSelect
                                        timeFormat="HH:mm"
                                        timeIntervals={15}
                                        minTime={new Date(new Date().setHours(0, 0))}
                                        maxDate={endDate ?? undefined}
                                        maxTime={
                                            endDate && startDate && new Date(startDate).toDateString() === new Date(endDate).toDateString()
                                                ? new Date(endDate.getTime() - 15 * 60000) // Horário máximo é 15 minutos antes do endDate
                                                : new Date(new Date().setHours(23, 59)) // Horário máximo padrão
                                        }
                                        dateFormat="MMMM d, yyyy h:mm aa"
                                        placeholderText='Appointment Start Date'
                                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full " required/>
                                </div>
                                <div>
                                    <label htmlFor="endDate" className="block text-sm font-medium text-gray-700 mb-2">End Date</label>
                                    <DatePicker
                                        selected={endDate}
                                        onChange={(endDate:Date) => setEndDate(endDate)}
                                        showTimeSelect
                                        timeFormat="HH:mm"
                                        timeIntervals={15}
                                        minDate={startDate ?? undefined}
                                        minTime={
                                            startDate && endDate && new Date(startDate).toDateString() === new Date(endDate).toDateString()
                                                ? new Date(new Date(startDate).getTime() + 15 * 60000) // Próximo intervalo de tempo
                                                : new Date(new Date().setHours(0, 0)) // Começa do início do dia para outros dias
                                        }
                                        maxTime={new Date(new Date().setHours(23, 59))}
                                        dateFormat="MMMM d, yyyy h:mm aa"
                                        placeholderText='Appointment End Date'

                                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full " required/>

                                </div>
                                <div>
                                    <label htmlFor="status"
                                           className="block text-sm font-medium text-gray-700 mb-2">Status</label>
                                    <select
                                        id="status"
                                        name="status"
                                        value={formData.status}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                    >
                                        <option value="SCHEDULED">SCHEDULED</option>
                                        <option value="COMPLETED">COMPLETED</option>
                                        <option value="CANCELLED">CANCELLED</option>
                                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                                    </select>
                                </div>
                            </div>
                            <div className="flex justify-end space-x-2 mt-6">
                                <button onClick={handleClose}
                                        className="px-4 py-2 rounded-md bg-gray-200 hover:bg-gray-300 transition-colors duration-200">Cancel
                                </button>
                                <button onClick={handleSave}
                                        className="px-4 py-2 rounded-md bg-pink-400 text-white hover:bg-pink-500 transition-colors duration-200">Save</button>
                            </div>
                        </>
                    )}
                </div>
            </div>
            
        </div>
        
    );
};

export default EditAppointmentModal;