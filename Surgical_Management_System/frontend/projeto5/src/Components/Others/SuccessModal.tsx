import React, { useEffect, useState } from 'react';
import { Check } from 'lucide-react';

interface SuccessModalProps {
    isOpen: boolean;
    onClose: () => void;
    message?: string;
}

const SuccessModal: React.FC<SuccessModalProps> = ({
                                                       isOpen,
                                                       onClose,
                                                       message = "Operation request updated successfully!"
                                                   }) => {
    const [isAnimating, setIsAnimating] = useState(false);
    const [isCheckmarkAnimating, setIsCheckmarkAnimating] = useState(false);

    useEffect(() => {
        if (isOpen) {
            setIsAnimating(true);
            setIsCheckmarkAnimating(true);
            // Fecha automaticamente após 2 segundos
            const timer = setTimeout(() => {
                handleClose();
            }, 2000);
            return () => clearTimeout(timer);
        }
    }, [isOpen]);

    const handleClose = () => {
        setIsAnimating(false);
        setIsCheckmarkAnimating(false);
        setTimeout(onClose, 300);
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
                        ? 'bg-opacity-30 transition-all duration-300'
                        : 'bg-opacity-0 transition-all duration-300'
                }`}
                onClick={handleClose}
            />

            <div
                className={`bg-white rounded-lg shadow-xl p-6 transform transition-all duration-300 relative
                    ${isAnimating && isOpen
                    ? 'translate-y-0 opacity-100 scale-100 rotate-0'
                    : 'translate-y-4 opacity-0 scale-95 rotate-12'}`}
            >
                <div className="flex flex-col items-center justify-center space-y-4">
                    <div
                        className={`rounded-full bg-green-100 p-3 transform transition-all duration-500
                            ${isCheckmarkAnimating
                            ? 'scale-100 rotate-0'
                            : 'scale-0 rotate-180'}`}
                    >
                        <Check
                            className={`w-8 h-8 text-green-500 transform transition-all duration-500 
                                ${isCheckmarkAnimating ? 'scale-100' : 'scale-0'}`}
                        />
                    </div>
                    <div
                        className={`text-center transform transition-all duration-300 delay-200
                            ${isCheckmarkAnimating ? 'translate-y-0 opacity-100' : 'translate-y-4 opacity-0'}`}
                    >
                        <h3 className="text-lg font-medium text-gray-900 mb-1">Success!</h3>
                        <p className="text-sm text-gray-500">{message}</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SuccessModal;