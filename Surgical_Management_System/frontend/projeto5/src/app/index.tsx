'use client'

import { Auth0Provider, useAuth0 } from '@auth0/auth0-react';
import { NextUIProvider } from '@nextui-org/react';
import React, { createContext, useContext, ReactNode } from 'react';

interface AuthContextType {
    user: any;
    isAuthenticated: boolean;
    isLoading: boolean;
    roles: string[];
}

const AuthContext = createContext<AuthContextType | null>(null);

export const useAuthContext = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error("useAuthContext must be used within an AuthContextProvider");
    }
    return context;
};

const AuthContextProvider = ({ children }: { children: ReactNode }) => {
    const { user, isAuthenticated, isLoading } = useAuth0();
    const roles = user ? (user['https://management-system.com/roles'] || []) : [];

    const value: AuthContextType = {
        user,
        isAuthenticated,
        isLoading,
        roles,
    };

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};

const AppProvider = ({ children }: { children: ReactNode }) => (
    <Auth0Provider
        domain="projeto5-grupo.eu.auth0.com"
        clientId="8jlW5IgJTE5R8GSZxRkajaPjAObZS45Z"
        authorizationParams={{
            redirect_uri: "http://localhost:3000/callback",
            audience: "https://projeto5-grupo.eu.auth0.com/api/v2/"
        }}
        skipRedirectCallback={true}
    >
        <NextUIProvider>
            <AuthContextProvider>{children}</AuthContextProvider>
        </NextUIProvider>
    </Auth0Provider>
);

export default AppProvider;