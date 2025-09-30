import { useRouter } from 'next/navigation';
import LoginForm from "@components/BackOffice User/LoginForm";
import React, {useState} from "react";
import styles from "./RegisterButton.module.css";



const RegisterButton = () => {
    const router = useRouter();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);
    const [isError, setError] = useState(false);
    const handleOpenModal = () => {
        setIsModalOpen(true);
    };
    const handleCloseModal = () => {
        setIsModalOpen(false);
    };
    return (

        <div>
            <button
                className={styles.btn}
                onClick={ () => {router.push("/Patient/RegisterHealthcarePatient");}}>
                Register for Health Care Application
            </button>

            {isModalOpen && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h2>Register</h2>
                        <p>Fill in the form to register for the Health Care Application.</p>
                        <button onClick={handleCloseModal}>Close</button>
                    </div>
                </div>
            )}
        </div>
    )
        ;
};

export default RegisterButton;
