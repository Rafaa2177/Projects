import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ user, role, children }) => {
    if (!user) {
        //redireciona para login
        return <Navigate to="/" />;
    }

    if (role && user.role !== role) {
        //se o role n for o correto vai para nao autorizado
        return <Navigate to="/unauthorized" />;
    }

    // se estiver tudo bem vai para o filho 
    return children;
};

export default ProtectedRoute;