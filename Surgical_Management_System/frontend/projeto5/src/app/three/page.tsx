// pages/index.tsx
"use client";
import dynamic from 'next/dynamic';
import React from 'react';

const ThreeComponent = dynamic(() => import('./ThreeScene'), {
    ssr: false, // Desativa o SSR para este componente
});

const Home: React.FC = () => {
    return (
        <div>
            <h1>Meu exemplo com Three.js e Next.js em TypeScript</h1>
            <ThreeComponent />

            
        </div>
    );
};

export default Home;
