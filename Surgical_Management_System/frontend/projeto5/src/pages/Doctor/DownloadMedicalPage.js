import React, { useEffect, useState } from "react";
import { jsPDF } from "jspdf";
/**
 * React component for downloading a patient's medical history as a PDF.
 * @param {Object} props - The component props.
 * @param {string} props.email - The email of the patient.
 * @param {Object} props.patientData - The patient's data.
 * @param {Array} props.medicalData - The patient's medical data.
 * @param {Array} props.allergiesData - The patient's allergies data.
 * @param {Array} props.medicalConditionsData - The patient's medical conditions data.
 */
const DownloadMedicalHistory = ({ email, patientData, medicalData, allergiesData, medicalConditionsData }) => {
    /**
     * State to manage the patient data.
     */
    const [setPatientData] = useState(null);

    /**
     * Fetches the patient data when the component is mounted or the email changes.
     */
    useEffect(() => {
        const fetchPatientData = async () => {
            if (email) {
                try {
                    const response = await fetch(`https://localhost:5001/api/Patients/${email}`);
                    if (!response.ok) throw new Error('Failed to fetch patient data');
                    const data = await response.json();
                    setPatientData(data);
                } catch (error) {
                    console.error('Error fetching patient data:', error);
                }
            }
        };

        fetchPatientData();
    }, [email]);

    /**
     * Downloads the patient's medical history as a PDF.
     */
    const downloadMedicalHistory = () => {
        if (!patientData && !allergiesData && !medicalConditionsData) {
            alert("No medical data available to download.");
            return;
        }

        const doc = new jsPDF();

        // Title
        doc.setFontSize(16);
        doc.text("Patient Medical Record", 105, 20, { align: "center" });

        // Patient Information
        doc.setFontSize(14);
        doc.text("Patient Information", 10, 40);

        doc.setFontSize(12);
        doc.text(`Name: ${patientData?.firstName || "N/A"}`, 10, 50);
        doc.text(`Date of Birth: ${patientData?.dateOfBirthFormatted || "N/A"}`, 10, 60);
        doc.text(`Phone: ${patientData?.phoneNumber || "N/A"}`, 10, 70);
        doc.text(`Email: ${email || "N/A"}`, 10, 80);

        const allergiesStartY = 110;
        doc.setFontSize(14);
        doc.text("Medical History", 10, allergiesStartY);
        doc.setFontSize(12);
        doc.text("Allergies:", 10, allergiesStartY + 10);
        if (allergiesData?.length > 0) {
            allergiesData.forEach((allergy, index) => {
                doc.text(`- ${allergy}`, 10, allergiesStartY + 20 + index * 10);
            });
        } else {
            doc.text("No allergies found.", 10, allergiesStartY + 20);
        }

        const conditionsStartY = allergiesStartY + 30 + (allergiesData?.length || 0) * 10;
        doc.setFontSize(12);
        doc.text("Chronic Conditions:", 10, conditionsStartY);
        if (medicalConditionsData?.length > 0) {
            medicalConditionsData.forEach((condition, index) => {
                doc.text(`- ${condition}`, 10, conditionsStartY + 10 + index * 10);
            });
        } else {
            doc.text("No chronic conditions found.", 10, conditionsStartY + 10);
        }

        doc.save(`${email}_medical_history.pdf`);
    };

    return (
        <button
            onClick={downloadMedicalHistory}
            className="bg-pink-700 text-white py-2 px-6 mt-4 rounded-lg hover:bg-pink-400 transition-colors"
        >
            Download Medical History
        </button>
    );
};

export default DownloadMedicalHistory;