import React, { useState, useEffect } from 'react';
import '../../app/globals.css';
import PatientDto from '../../dtos/PatientDto';
import DoctorDto from '../../dtos/DoctorDto';
const OperationForm = ({ onSubmit, initialData = {} }) => {
    const [formData, setFormData] = useState({
        PatientId: "",
        DoctorId: "",
        OperationType: "",
        OperationDate: "",
        Priority: "",
        ...initialData,
    });

    const [patients, setPatients] = useState([]);
    const [doctors, setDoctors] = useState([]);

    // Fetch patients and doctors from the API
    useEffect(() => {
        const fetchPatients = async () => {
            try {
                const response = await fetch('https://localhost:5001/api/Patients');
                if (!response.ok) {
                    throw new Error('Failed to fetch patients');
                }
                const data = await response.json();
                const patientDTOs = data.map(patient => ({
                    ...PatientDto,
                    id: patient.id,
                    firstName: patient.firstName,
                    lastName: patient.lastName,
                    fullName: patient.fullName,
                    dateOfBirth: patient.dateOfBirth,
                    dateOfBirthFormatted: patient.dateOfBirthFormatted,
                    email: patient.email,
                    phoneNumber: patient.phoneNumber,
                    emergencyContact: patient.emergencyContact,
                    gender: patient.gender,
                    medicalConditions: patient.medicalConditions,
                    appointments: patient.appointments,
                    recordNumber: patient.recordNumber
                }));
                setPatients(patientDTOs);
            } catch (error) {
                console.error('Error fetching patients:', error);
            }
        };

        const fetchDoctors = async () => {
            try {
                const response = await fetch('https://localhost:5001/api/Staffs/active');
                if (!response.ok) {
                    throw new Error('Failed to fetch doctors');
                }
                const data = await response.json();
                const doctorDTOs = data
                    .filter(doctor => doctor.role === 0)
                    .map(doctor => ({
                        ...DoctorDto,
                        id: doctor.id,
                        firstName: doctor.firstName,
                        lastName: doctor.lastName,
                        fullName: doctor.fullName,
                        email: doctor.email,
                        phoneNumber: doctor.phoneNumber,
                        licenseNumber: { value: doctor.licenseNumber.value },
                        role: doctor.role,
                        specialization: doctor.specialization,
                        timeSlots: doctor.timeSlots
                    }));
                setDoctors(doctorDTOs);
            } catch (error) {
                console.error('Error fetching doctors:', error);
            }
        };

        fetchPatients();
        fetchDoctors();
    }, []);

    // Handle input change
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevData => ({ ...prevData, [name]: value }));
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit}
              className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg">
            <select
                name="PatientId"
                value={formData.PatientId}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="" disabled>Select Patient</option>
                {patients.map(patient => (
                    <option key={patient.id} value={patient.id}>
                        {patient.recordNumber.value}
                    </option>
                ))}
            </select>
            <select
                name="DoctorId"
                value={formData.DoctorId}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="" disabled>Select Doctor</option>
                {doctors.map(doctor => (
                    <option key={doctor.id} value={doctor.id}>
                        {doctor.licenseNumber.value}
                    </option>
                ))}
            </select>
            <select
                name="OperationType"
                value={formData.OperationType}
                onChange={handleChange}
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
            <input
                type="date"
                name="OperationDate"
                value={formData.OperationDate}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            />
            <select
                name="Priority"
                value={formData.Priority}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="" disabled>Select Priority</option>
                <option value="Low">Low</option>
                <option value="Medium">Medium</option>
                <option value="High">High</option>
                <option value="Very High">Very High</option>
            </select>
            <button
                type="submit"
                className="bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors mt-4 text-xl w-full max-w-2xl mx-auto"
            >
                Submit Operation Request
            </button>
        </form>
    );
};

export default OperationForm;