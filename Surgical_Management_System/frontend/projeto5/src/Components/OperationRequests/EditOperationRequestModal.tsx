import React, { useEffect, useState } from 'react';

interface OperationRequestDto {
    doctorId: string;
    patientId: string;
    operationType: string;
    operationDate: string;
    priority: string;
}

interface EditOperationRequestModalProps {
    operationRequest: OperationRequestDto | null;
    isOpen: boolean;
    onClose: () => void;
    onSave: (updatedRequest: OperationRequestDto) => void;
}

const EditOperationRequestModal: React.FC<EditOperationRequestModalProps> = ({
                                                                                 operationRequest,
                                                                                 isOpen,
                                                                                 onClose,
                                                                                 onSave,
                                                                             }) => {
    const [formData, setFormData] = useState<OperationRequestDto | null>(operationRequest);
    const [isAnimating, setIsAnimating] = useState(false);

    useEffect(() => {
        if (operationRequest) {
            // Cria uma cópia do objeto para não modificar o original
            const formattedRequest = { ...operationRequest };

            // Formata a data para o formato yyyy-MM-dd
            if (formattedRequest.operationDate) {
                formattedRequest.operationDate = formatDateForInput(formattedRequest.operationDate);
            }

            setFormData(formattedRequest);
        }
    }, [operationRequest]);

    useEffect(() => {
        if (isOpen) {
            setIsAnimating(true);
        }
    }, [isOpen]);

    // Função para formatar a data do formato ISO para yyyy-MM-dd
    const formatDateForInput = (dateString: string): string => {
        if (!dateString) return '';

        try {
            const date = new Date(dateString);
            return date.toISOString().split('T')[0];
        } catch (error) {
            console.error('Error formatting date:', error);
            return '';
        }
    };

    // Função para formatar a data de volta para o formato do backend
    const formatDateForBackend = (dateString: string): string => {
        if (!dateString) return '';

        try {
            const date = new Date(dateString);
            return date.toISOString();
        } catch (error) {
            console.error('Error formatting date for backend:', error);
            return '';
        }
    };

    const handleClose = () => {
        setIsAnimating(false);
        setTimeout(onClose, 300);
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        if (formData) {
            const { name, value } = e.target;
            setFormData(prev => {
                if (!prev) return null;

                // Se for o campo de data, mantém o formato yyyy-MM-dd no estado
                if (name === 'operationDate') {
                    return { ...prev, [name]: value };
                }

                return { ...prev, [name]: value };
            });
        }
    };

    const handleSave = () => {
        if (formData) {
            // Cria uma cópia do formData para não modificar o estado diretamente
            const dataToSave = { ...formData };

            // Converte a data de volta para o formato do backend antes de salvar
            if (dataToSave.operationDate) {
                dataToSave.operationDate = formatDateForBackend(dataToSave.operationDate);
            }

            onSave(dataToSave);
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
                    <h2 className="text-xl font-bold mb-4">Edit Operation Request</h2>
                    {formData && (
                        <>
                            <div className="space-y-4">
                                <div>
                                    <label
                                        htmlFor="doctorId"
                                        className="block text-sm font-medium text-gray-700 mb-2">
                                        Doctor ID
                                    </label>
                                    <input
                                        type="text"
                                        id="doctorId"
                                        name="doctorId"
                                        value={formData.doctorId}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200 disabled"
                                        disabled={true}
                                    />
                                </div>
                                <div>
                                    <label
                                        htmlFor="patientId"
                                        className="block text-sm font-medium text-gray-700 mb-2"
                                    >
                                        Patient ID
                                    </label>
                                    <input
                                        type="text"
                                        id="patientId"
                                        name="patientId"
                                        value={formData.patientId}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                        disabled={true}
                                    />
                                </div>
                                <div>
                                    <label
                                        htmlFor="operationType"
                                        className="block text-sm font-medium text-gray-700 mb-2"
                                    >Operation Type</label>
                                    <input
                                        data-testid="operation-type-input"
                                        type="text"
                                        id="operationType"
                                        name="operationType"
                                        value={formData.operationType}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                    />
                                </div>
                                <div>
                                    <label
                                        htmlFor="operationDate"
                                        className="block text-sm font-medium text-gray-700 mb-2"
                                    >
                                        Operation Date
                                    </label>
                                    <input
                                        type="date"
                                        id="operationDate"
                                        name="operationDate"
                                        value={formData.operationDate}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                    />
                                </div>
                                <div>
                                    <label
                                        htmlFor="priority"
                                        className="block text-sm font-medium text-gray-700 mb-2"
                                    >
                                        Priority
                                    </label>
                                    <select
                                        id="priority"
                                        name="priority"
                                        value={formData.priority}
                                        onChange={handleChange}
                                        className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-pink-300 focus:border-pink-300 transition-colors duration-200"
                                    >
                                        <option value="Very High">Very High</option>
                                        <option value="High">High</option>
                                        <option value="Medium">Medium</option>
                                        <option value="Low">Low</option>
                                    </select>
                                </div>
                            </div>
                            <div className="flex justify-end space-x-2 mt-6">
                                <button
                                    onClick={handleClose}
                                    className="px-4 py-2 rounded-md bg-gray-200 hover:bg-gray-300 transition-colors duration-200"
                                >
                                    Cancel
                                </button>
                                <button
                                    onClick={handleSave}
                                    className="px-4 py-2 rounded-md bg-pink-400 text-white hover:bg-pink-500 transition-colors duration-200"
                                >
                                    Save
                                </button>
                            </div>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

export default EditOperationRequestModal;