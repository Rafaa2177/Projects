import React from "react";
import EditOperationRequestModal from "@components/OperationRequests/EditOperationRequestModal";

const DeleteOperationRequestButton = ({ id }: { id: string }) => {
    async function handleRemove(){
        const shouldRemove = confirm("are you sure you want to delete? Request id:" + id);
        if (shouldRemove) {
            if (localStorage.getItem('docId') != null) {
                await fetch(`https://localhost:5001/api/OperationRequest/${localStorage.getItem('docId')}/${localStorage.getItem('accessToken')}/${id}`, {
                    method: 'DELETE',
                }).then(response => {
                    if (response.ok) {
                        alert("Request deleted successfully");
                        window.location.reload();
                    } else {
                        alert("Error deleting request" + response.statusText);
                    }
                });
            }
        }
    }
    return (
        <button
            className="bg-pink-400 text-white py-2 px-5 rounded-lg float-right ml-2  -mt-8"
            onClick={() => handleRemove()}>
            Apagar
        </button>
    
    )


};
export default DeleteOperationRequestButton;
