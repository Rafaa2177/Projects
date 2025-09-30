import * as THREE from "three";
import { GLTFLoader } from "three/addons/loaders/GLTFLoader.js";
import TWEEN from "@tweenjs/tween.js";

export default class Bed {

    constructor(parameters) {
        

        

        for (const [key, value] of Object.entries(parameters)) {
            this[key] = value;
        }

        this.onLoad = (gltf) => {
            this.object = gltf.scene;
            this.setShadow(this.object);

            // Define o centro da sala correspondente a esta cama
            this.object.userData.roomCenter = this.roomCenter;

            // Adiciona evento de clique para seleção
            this.object.traverse((child) => {
                if (child instanceof THREE.Mesh) {
                    child.userData.parentBed = this; // Associa o Bed para lógica futura
                }
            });

            this.addClickEvent();
            this.loaded = true;
        };

        this.onProgress = (url, xhr) => {
            console.log("Resource '" + url + "' " + (100.0 * xhr.loaded / xhr.total).toFixed(0) + "% loaded.");
        };

        this.onError = (url, error) => {
            console.error("Error loading resource " + url + " (" + error + ").");
        };

        const loader = new GLTFLoader();
        loader.load(this.url, this.onLoad, (xhr) => this.onProgress(this.url, xhr), (error) => this.onError(this.url, error));
    }

    setShadow(object) {
        object.traverseVisible((child) => {
            if (child instanceof THREE.Object3D) {
                child.castShadow = true;
                child.receiveShadow = false;
            }
        });
    }


   /* // Mover câmera para o centro da sala
    moveCameraToRoomCenter() {
        const roomCenter = this.object.userData.roomCenter;

        if (!roomCenter) {
            console.warn("Esta cama não tem um centro de sala definido.");
            return;
        }

        // Define o alvo da câmera mantendo a altura atual
        const targetPosition = new THREE.Vector3(roomCenter.x, this.camera.position.y, roomCenter.z);

        // Movimento suave usando TWEEN
        new TWEEN.Tween(this.camera.position)
            .to(targetPosition, 1000) // 1 segundo de transição
            .easing(TWEEN.Easing.Quadratic.InOut)
            .start();
    }*/
}