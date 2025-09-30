import * as THREE from "three";
import Ground from "./ground.js";
import Wall from "./wall.js";
import Bed from "@/pages/Hospital3d/bed";
import {GLTFLoader} from "three/addons/loaders/GLTFLoader.js";
import { runInThisContext } from "vm";



/*
 * parameters = {
 *  url: String,
 *  credits: String,
 *  scale: Vector3
 * }
 */

export default class Maze {
    constructor(parameters,camera,appointments) {
        this.camera=camera;
        this.appointments=appointments;
        this.onLoad = function (description) {
            // Store the maze's map and size
            this.map = description.map;
            this.size = description.size;

            // Store the player's initial position and direction
            this.initialPosition = this.cellToCartesian(description.initialPosition);
            this.initialDirection = description.initialDirection;

            // Store the maze's exit location
            this.exitLocation = this.cellToCartesian(description.exitLocation);

            // Create a group of objects
            this.object = new THREE.Group();

            // Create the ground
            this.ground = new Ground({ textureUrl: description.groundTextureUrl, size: description.size });
            this.object.add(this.ground.object);

            // Create a wall
            this.wall = new Wall({ textureUrl: description.wallTextureUrl });
            this.bed = new Bed({ url: description.bedObject, scale: new THREE.Vector3(0.1, 0.1, 0.1) });
           
            let wallObject;
         const middleRow = Math.floor(description.size.height / 2); // Linha central
            const corridorWidth = 2; // Define a largura do corredor (ajustável)

            // Adiciona a parede ao longo da linha central, com um espaço para o corredor
            for (let i = 0; i <= description.size.width - 1; i++) {
                // Verifica se a posição atual está dentro do corredor
                if (i < middleRow - corridorWidth / 2 || i > middleRow - 1 + corridorWidth / 2) {
                    // Paredes do lado esquerdo do corredor
                    wallObject = this.wall.object.clone();
                    if (i==2){ 
                        wallObject.scale.set(0.6, 1,1);
                        wallObject.position.set(i - description.size.width / 2.0 + 0.27, 0.5, middleRow - description.size.height / 2.0 - corridorWidth / 2);
                    } else if (i==5){
                        wallObject.scale.set(0.6, 1,1);
                        wallObject.position.set(i - description.size.width / 2.0 + 0.7, 0.5, middleRow - description.size.height / 2.0 - corridorWidth / 2);
                    }
                    else wallObject.position.set(i - description.size.width / 2.0 + 0.5, 0.5, middleRow - description.size.height / 2.0 - corridorWidth / 2);
                    this.object.add(wallObject);

                    // Paredes do lado direito do corredor
                    wallObject = this.wall.object.clone();
                    if (i==2){
                        wallObject.scale.set(0.6, 1,1);
                        wallObject.position.set(
                            i - description.size.width / 2.0 + 0.27,
                            0.5,
                            middleRow - description.size.height / 2.0 + corridorWidth / 2
                        );
                    } else if (i==5){
                        wallObject.scale.set(0.6, 1,1);
                        wallObject.position.set(
                            i - description.size.width / 2.0 + 0.7,
                            0.5,
                            middleRow - description.size.height / 2.0 + corridorWidth / 2
                        );
                    } else 
                    wallObject.position.set(
                        i - description.size.width / 2.0 + 0.5,
                        0.5,
                        middleRow - description.size.height / 2.0 + corridorWidth / 2
                    );
                    this.object.add(wallObject);
                }
                }
            const loader = new GLTFLoader();
            if (this.appointments.length > 0) {
                this.appointments.forEach(appointment => {
                    
                        let room = appointment.room;
                        switch (room) {
                            case "or1":
                                loader.load("/models/gltf/paciente/paciente.glb",
                                    (gltf) => {
                                        gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                        gltf.scene.rotateX(-Math.PI / 2);
                                        gltf.scene.rotateZ(Math.PI);
                                        gltf.scene.position.set(description.size.width / 4.0 - 4.5, 0.75, middleRow - description.size.height / 6.0 + corridorWidth / 2 - 1.4);
                                        this.setShadow(gltf.scene);
                                        this.object.add(gltf.scene);
                                    }
                                );
                                break;
                            case "or4":
                                loader.load("/models/gltf/paciente/paciente.glb",
                                    (gltf) => {
                                        gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                        gltf.scene.rotateX(Math.PI / 2);
                                        gltf.scene.rotateY(Math.PI);
                                        gltf.scene.position.set(description.size.width / 1.2 - 4.05, 0.75, middleRow - description.size.height / 6.0 + corridorWidth / 2 - 1.4);
                                        this.setShadow(gltf.scene);
                                        this.object.add(gltf.scene);
                                    }
                                );
                                break;
                            case "or3":
                                loader.load("/models/gltf/paciente/paciente.glb",
                                    (gltf) => {
                                        gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                        gltf.scene.rotateX(-Math.PI / 2);
                                        gltf.scene.position.set(description.size.width / 1.2 - 4.05, 0.75, middleRow - description.size.height / 1.2 + corridorWidth / 2 - 0.63);
                                        this.setShadow(gltf.scene);
                                        this.object.add(gltf.scene);
                                    }
                                );
                                break;
                            case "or2":
                                loader.load("/models/gltf/paciente/paciente.glb",
                                    (gltf) => {
                                        gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                        gltf.scene.rotateX(-Math.PI / 2);
                                        gltf.scene.position.set(description.size.width / 4 - 4.5, 0.75, middleRow - description.size.height / 1.2 + corridorWidth / 2 - 0.63);
                                        this.setShadow(gltf.scene);
                                        this.object.add(gltf.scene);
                                    }
                                );
                                break;
                        }
                    
                })
            }

                /*for (let i=0;i<description.numRooms;i++){
                if (description.roomsOcupied[i]) {
                    switch (i){
                        case 0:
                            loader.load("/models/gltf/paciente/paciente.glb",
                                (gltf)=> {
                                    gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                    gltf.scene.rotateX(-Math.PI / 2);
                                    gltf.scene.rotateZ(Math.PI);
                                    gltf.scene.position.set(description.size.width / 4.0 - 4.5, 0.75, middleRow - description.size.height / 6.0 + corridorWidth / 2 - 1.4);
                                    this.setShadow(gltf.scene);
                                    this.object.add(gltf.scene);
                                }
                            );
                            break;
                        case 1:
                            loader.load("/models/gltf/paciente/paciente.glb",
                                (gltf)=> {
                                    gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                    gltf.scene.rotateX(-Math.PI / 2);
                                    gltf.scene.position.set(description.size.width / 4 -4.5,0.75,middleRow - description.size.height / 1.2 + corridorWidth / 2-0.63);
                                    this.setShadow(gltf.scene);
                                    this.object.add(gltf.scene);
                                }
                            );
                            break;
                        case 2:
                            loader.load("/models/gltf/paciente/paciente.glb",
                                (gltf)=> {
                                    gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                    gltf.scene.rotateX(-Math.PI / 2);
                                    gltf.scene.position.set(description.size.width / 1.2 -4.05,0.75,middleRow - description.size.height / 1.2 + corridorWidth / 2-0.63);
                                    this.setShadow(gltf.scene);
                                    this.object.add(gltf.scene);
                                }
                            );
                            break;
                        case 3:
                            loader.load("/models/gltf/paciente/paciente.glb",
                                (gltf)=> {
                                    gltf.scene.scale.set(0.005, 0.0035, 0.009);
                                    gltf.scene.rotateX(Math.PI / 2);
                                    gltf.scene.rotateY(Math.PI);
                                    gltf.scene.position.set(description.size.width / 1.2 -4.05,0.75,middleRow - description.size.height / 6.0 + corridorWidth / 2-1.4);
                                    this.setShadow(gltf.scene);
                                    this.object.add(gltf.scene);
                                }
                            );
                            break;
                    }
                }
            }*/
            
            loader.load(
                "/models/gltf/Door/scene.gltf",
                (gltf) => {
                    gltf.scene.scale.set(0.000593, 0.00081, 0.0001); //Redimensionar porta
                    gltf.scene.position.set( description.size.width / 6.0+0.07, 0.01, middleRow - description.size.height / 2.0 - corridorWidth / 2);
                    this.setShadow(gltf.scene);
                    this.object.add(gltf.scene);

                    let aux = gltf.scene.clone();
                    gltf.scene.position.set( description.size.width / 5.7, 0.01, middleRow - description.size.height / 2.0 - corridorWidth / 2 + 2);
                    this.object.add(aux);

                    let aux1 = gltf.scene.clone();
                    gltf.scene.position.set( description.size.width / 5.7 - 2.43, 0.01, middleRow - description.size.height / 2.0 - corridorWidth / 2);        
                    this.object.add(aux1);


                    let aux2 = gltf.scene.clone();
                    gltf.scene.position.set( description.size.width / 5.7 - 2.43, 0.01, middleRow - description.size.height / 2.0 - corridorWidth / 2+ 2);
                    this.object.add(aux2);
                    
                    let aux3 = gltf.scene.clone();
                    gltf.scene.position.set( description.size.width / 17 - 0.9 , 0.01, middleRow - description.size.height / 2.0 - corridorWidth / 2 + 4.99 );
                    this.object.add(aux3);
                    
                    let aux4 = gltf.scene.clone();
                    aux4.rotateY(Math.PI);
                    gltf.scene.position.set( description.size.width / 17 -0.06 , 0.01, middleRow - description.size.height / 2.0 - corridorWidth / 2 + 4.99);
                    this.object.add(aux4);
                }
                
                
                
            )
            loader.load(
                "/models/gltf/scene.gltf",
                (gltf) => { // Change to arrow function
                    gltf.scene.scale.set(0.005, 0.005, 0.005);
            
                    // Crie um contador para numerar as camas
                    let bedCounter = 0;
            
                    // Posicione o modelo original
                    gltf.scene.position.set(description.size.width / 4.0 - 4.5, 0.5, middleRow - description.size.height / 6.0 + corridorWidth / 2 - 1);
                    gltf.scene.name = `bed_${bedCounter++}`; // Nomeia a cama original
                    this.setShadow(gltf.scene);
                    this.object.add(gltf.scene);
            
                    // Clone e nomeie as camas
                    let aux = gltf.scene.clone();
                    aux.rotateY(Math.PI);
                    aux.position.set(description.size.width / 4.0 - 4.5, 0.5, middleRow - description.size.height / 1.2 + corridorWidth / 2 - 1);
                    aux.name = `bed_${bedCounter++}`;
                    this.object.add(aux);
            
                    let aux1 = gltf.scene.clone();
                    aux1.rotateY(Math.PI);
                    aux1.position.set(description.size.width / 1.2 - 4.05, 0.5, middleRow - description.size.height / 1.2 + corridorWidth / 2 - 1);
                    aux1.name = `bed_${bedCounter++}`;
                    this.object.add(aux1);
            
                    let aux2 = gltf.scene.clone();
                    aux2.position.set(description.size.width / 1.2 - 4.05, 0.5, middleRow - description.size.height / 6 + corridorWidth / 2 - 1);
                    aux2.name = `bed_${bedCounter++}`;
                    this.object.add(aux2);
                },
                undefined,
                (error) => {
                    console.error(error);
                }
            );
            
            this.raycaster = new THREE.Raycaster();
            this.mouse = new THREE.Vector2();
            this.currentView = "default";
    
            let selectedRoom = null;
            
            // Adiciona um listener para cliques do mouse
            window.addEventListener("click", (event) => {
                
                // Normaliza as coordenadas do clique do mouse
                this.mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
                this.mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
                console.log("Clique detetado, "+this.mouse.x +" "+ this.mouse.y);
                // Configura o raycaster
                this.raycaster.setFromCamera(this.mouse, this.camera.object);
    
                // Verifica interseção com objetos no labirinto
                const intersects = this.raycaster.intersectObjects(this.object.children, true);
    
                if (intersects.length > 0) {
                    // Encontra o objeto pai mais próximo que tem um nome
                    let targetObject = intersects[0].object;
                    while (targetObject.parent && !targetObject.name.includes('bed_')) {
                        targetObject = targetObject.parent;
                    }
    
                    // Se encontrou uma cama e não está em vista top-down
                    if (targetObject.name.includes('bed_') && this.currentView !== "top-down") {
                        this.camera.setTopDownView(targetObject.position);
                        this.currentView = "top-down";
                        selectedRoom = targetObject.name;
                    } else if (this.currentView === "top-down") {
                        selectedRoom = targetObject.name;
                        console.log("Quarto selecionado: " + selectedRoom);
                        // Clicou em qualquer outro lugar enquanto estava em top-down
                        this.camera.resetToDefaultView();
                        this.currentView = "default";
                    }
                } else if (this.currentView === "top-down") {
                    // Clicou no vazio enquanto estava em top-down
                    this.camera.resetToDefaultView();
                    this.currentView = "default";
                }
            });

            const overlay = document.createElement('div');
            overlay.id = 'overlay';
            overlay.style.position = 'fixed';
            overlay.style.top = '0';
            overlay.style.left = '0';
            overlay.style.width = '100%';
            overlay.style.height = '100%';
            overlay.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
            overlay.style.color = 'white';
            overlay.style.display = 'flex';
            overlay.style.justifyContent = 'center';
            overlay.style.alignItems = 'center';
            overlay.style.fontSize = '1.5em';
            overlay.style.overflowY = 'auto';
            overlay.style.padding = '20px';
            overlay.style.boxSizing = 'border-box';
            overlay.style.display = 'none';

            const textContainer = document.createElement('div');
            textContainer.style.maxWidth = '100%'; 
            textContainer.style.maxHeight = '80%'; 
            textContainer.style.overflowY = 'auto'; 
            textContainer.style.padding = '10px'; 
            textContainer.style.backgroundColor = 'rgba(0, 0, 0, 0)';
            textContainer.style.borderRadius = '10px'; 

            overlay.appendChild(textContainer);
            document.body.appendChild(overlay);

            window.addEventListener("keydown", (event) => {
                if (event.key === "i" && this.currentView === "top-down") {
                    if (selectedRoom) {
                        const roomNumber = parseInt(selectedRoom.match(/\d+/)[0], 10) + 1; // Extract and increment the room number
                        const roomName="or"+roomNumber;
                        let c=0;
                        appointments.forEach(appointment => {
                            if (appointment.room === roomName) {
                                textContainer.innerText = `Informações sobre a sala de operação nº${roomNumber}\n\n` +
                                    `ID: ${appointment.id}\n` +
                                    `Doctor ID: ${appointment.doctorName}\n` +
                                    `Pacient ID: ${appointment.pacientName}\n` +
                                    `Start Date: ${new Date(appointment.startDate).toLocaleString()}\n` +
                                    `End Date: ${new Date(appointment.endDate).toLocaleString()}\n` +
                                    `Operation Type: ${appointment.operationType}\n` +
                                    `Status: ${appointment.status}`;
                                
                                c++;
                            }
                        });
                        if (c==0) {
                            textContainer.innerText = `Informações sobre a sala de operação nº${roomNumber}\nNenhuma consulta encontrada.`;
                            
                        }
                        overlay.style.display = overlay.style.display === "none" ? "flex" : "none";
                    } 
                        
                }
                if (event.key === "i" && this.currentView !== "top-down") {
                    overlay.style.display = "none";
                }
            });
            
            // Adiciona paredes ao longo do perímetro
            for (let i = 0; i < description.size.width; i++) {
                // Paredes nas bordas superior e inferior (horizontal)
                let wallTop = this.wall.object.clone();
                wallTop.position.set(i - description.size.width / 2.0 + 0.5, 0.5, -description.size.height / 2.0);
                this.object.add(wallTop);

                let wallBottom = this.wall.object.clone();
                if(i==3 ){
                    wallBottom.scale.set(0.6,1,1);
                    wallBottom.position.set(i - description.size.width / 2.0 + 0.3 , 0.5, description.size.height / 2.0);
                    
                } else if (i==4){
                    wallBottom.scale.set(0.6,1,1);
                    wallBottom.position.set(i - description.size.width / 2.0 + 0.7 , 0.5, description.size.height / 2.0);
                } else
                wallBottom.position.set(i - description.size.width / 2.0 + 0.5, 0.5, description.size.height / 2.0);
                this.object.add(wallBottom);
            }

            for (let j = 0; j <= description.size.height - 1; j++) {
                // Verifica se a posição atual está dentro do corredor
                if (j < middleRow - corridorWidth / 2 || j > middleRow - 1 + corridorWidth / 2) {
                    wallObject = this.wall.object.clone();
                    wallObject.rotateY(Math.PI / 2); // Rotaciona para criar uma parede vertical
                    wallObject.position.set(
                        middleRow - description.size.width / 2 + corridorWidth / 2,
                        0.5,
                        j - description.size.height / 2.0 + 0.5
                    );
                    this.object.add(wallObject);
                }
                if (j < middleRow - corridorWidth / 2 || j > middleRow - 1 + corridorWidth / 2) {
                    wallObject = this.wall.object.clone();
                    wallObject.rotateY(Math.PI / 2); // Rotaciona para criar uma parede vertical
                    wallObject.position.set(
                        middleRow - description.size.width / 2 - corridorWidth / 2,
                        0.5,
                        j - description.size.height / 2.0 + 0.5
                    );
                    this.object.add(wallObject);
                }
            }

            for (let j = 0; j < description.size.height; j++) {
                // Paredes nas bordas esquerda e direita (vertical)
                let wallLeft = this.wall.object.clone();
                wallLeft.rotateY(Math.PI / 2.0);
                wallLeft.position.set(-description.size.width / 2.0, 0.5, j - description.size.height / 2.0 + 0.5);
                this.object.add(wallLeft);

                let wallRight = this.wall.object.clone();
                wallRight.rotateY(Math.PI / 2.0);
                wallRight.position.set(description.size.width / 2.0 , 0.5, j - description.size.height / 2.0 + 0.5);
                this.object.add(wallRight);
            }


            this.object.scale.set(this.scale.x, this.scale.y, this.scale.z);
            this.loaded = true;
        }

        this.onProgress = function (url, xhr) {
            console.log("Resource '" + url + "' " + (100.0 * xhr.loaded / xhr.total).toFixed(0) + "% loaded.");
        }

        this.onError = function (url, error) {
            console.error("Error loading resource " + url + " (" + error + ").");
        }

        for (const [key, value] of Object.entries(parameters)) {
            this[key] = value;
        }
        this.loaded = false;
        // The cache must be enabled; additional information available at https://threejs.org/docs/api/en/loaders/FileLoader.html
        THREE.Cache.enabled = true;

        // Create a resource file loader
        const loader = new THREE.FileLoader();

        // Set the response type: the resource file will be parsed with JSON.parse()
        loader.setResponseType("json");

        // Load a maze description resource file
        loader.load(
            //Resource URL
            this.url,

            // onLoad callback
            description => this.onLoad(description),

            // onProgress callback
            xhr => this.onProgress(this.url, xhr),

            // onError callback
            error => this.onError(this.url, error)
        );
    }

    // Convert cell [row, column] coordinates to cartesian (x, y, z) coordinates
    cellToCartesian(position) {
        return new THREE.Vector3((position[1] - this.size.width / 2.0 + 0.5) * this.scale.x, 0.0, (position[0] - this.size.height / 2.0 + 0.5) * this.scale.z)
    }

    // Convert cartesian (x, y, z) coordinates to cell [row, column] coordinates
    cartesianToCell(position) {
        return [Math.floor(position.z / this.scale.z + this.size.height / 2.0), Math.floor(position.x / this.scale.x + this.size.width / 2.0)];
    }

    distanceToWestWall(position) {
        const indices = this.cartesianToCell(position);
        if (this.map[indices[0]][indices[1]] == 1 || this.map[indices[0]][indices[1]] == 3) {
            return position.x - this.cellToCartesian(indices).x + this.scale.x / 2.0;
        }
        return Infinity;
    }

    distanceToEastWall(position) {
        const indices = this.cartesianToCell(position);
        indices[1]++;
        if (this.map[indices[0]][indices[1]] == 1 || this.map[indices[0]][indices[1]] == 3) {
            return this.cellToCartesian(indices).x - this.scale.x / 2.0 - position.x;
        }
        return Infinity;
    }

    distanceToNorthWall(position) {
        const indices = this.cartesianToCell(position);
        if (this.map[indices[0]][indices[1]] == 2 || this.map[indices[0]][indices[1]] == 3) {
            return position.z - this.cellToCartesian(indices).z + this.scale.z / 2.0;
        }
        return Infinity;
    }

    distanceToSouthWall(position) {
        const indices = this.cartesianToCell(position);
        indices[0]++;
        if (this.map[indices[0]][indices[1]] == 2 || this.map[indices[0]][indices[1]] == 3) {
            return this.cellToCartesian(indices).z - this.scale.z / 2.0 - position.z;
        }
        return Infinity;
    }

    foundExit(position) {
        return Math.abs(position.x - this.exitLocation.x) < 0.5 * this.scale.x && Math.abs(position.z - this.exitLocation.z) < 0.5 * this.scale.z
    };
    setShadow(object) {
        object.traverseVisible(function (child) { // Modifying the scene graph inside the callback is discouraged: https://threejs.org/docs/index.html?q=object3d#api/en/core/Object3D.traverseVisible
            if (child instanceof THREE.Object3D) {
                child.castShadow = true;
                child.receiveShadow = false;
            }
        });
    }
}
