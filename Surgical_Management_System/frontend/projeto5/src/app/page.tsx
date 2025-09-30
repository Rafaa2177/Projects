'use client'

import React, {useEffect, useState} from 'react';
import { Modal } from "@/Components/Modal";
import Image from "next/image";
import styles from './Page.module.css';
import styles1 from './LoginButton.module.css';
import RegisterButton from "@/Components/Pacient/RegisterButton";

export default function Home() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    useEffect(() => {
        
            localStorage.clear();
        
    }, []);
    return (
        <div
            className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]"
            style={{
                backgroundImage: "url('/4863428.jpg')",
                backgroundSize: "cover",
                backgroundPosition: "center",
            }}
        >
            <div
                className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center  p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)] w-full h-screen">
                <main className="flex flex-col gap-8 row-start-10 items-center sm:items-start">
                    <a
                        href="/BackOfficeUser/LoginPage"
                        className={styles1.btnLogin}
                    >
                        Login
                    </a>
                    
                    <RegisterButton/> 
                </main>
                <footer className="row-start-13 flex gap-6 flex-wrap items-center justify-center">
                    <a
                        className="flex items-center gap-2 hover:underline hover:underline-offset-4"
                        href="#"
                        onClick={() => setIsModalOpen(true)}
                    >
                        <Image
                            aria-hidden
                            src="/file.svg"
                            alt="File icon"
                            width={16}
                            height={16}
                        />
                        About us
                    </a>
                    <a
                        className="flex items-center gap-2 hover:underline hover:underline-offset-4"
                        href="https://www.isep.ipp.pt"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        <Image
                            aria-hidden
                            src="/globe.svg"
                            alt="Globe icon"
                            width={16}
                            height={16}
                        />
                        Go to isep.ipp.pt →
                    </a>
                </footer>
            </div>
            <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
                <div className={styles.pageContainer}>
                    <h2 className={styles.pageHeader}>About Us</h2>
                    <div className={styles.pageContent}>
                        <p>Francisca Teixeira</p>
                        <p>Matilde Händel</p>
                        <p>Rodrigo Santos</p>
                        <p>Rafaela Lopes</p>
                    </div>
                </div>
            </Modal>
        </div>
    );
}