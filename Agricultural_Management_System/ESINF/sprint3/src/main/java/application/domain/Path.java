package application.domain;

import java.time.LocalTime;
import java.util.LinkedList;

public class Path {

        private LinkedList<Localidades> pathStops;
        private double pathDistance;
        private double duration;    //minutos
        private LinkedList<Localidades> remainingHubs;  //hubs no momento de chegada a um determinado destino
        private LinkedList<Localidades> rechargeStops;
        private LinkedList<LocalTime> hoursInformation;

        public Path(LinkedList<Localidades> stops, double distance) {
            this.pathDistance = distance;
            this.pathStops = stops;
            this.duration = 0;
            this.remainingHubs = new LinkedList<>();
            this.rechargeStops = new LinkedList<>();
        }

        public Path(LinkedList<Localidades> stops, double distance, double duration, LinkedList<Localidades> remainingHubs, LinkedList<Localidades> rechargeStops) {
            this.pathDistance = distance;
            this.pathStops = stops;
            this.duration = duration;
            this.remainingHubs = remainingHubs;
            this.rechargeStops = rechargeStops;
            this.hoursInformation = new LinkedList<>();
        }

        public Path(LinkedList<Localidades> pathStops, double pathDistance, double duration, LinkedList<Localidades> remainingHubs, LinkedList<Localidades> rechargeStops, LinkedList<LocalTime> hoursInformation) {
            this.pathStops = pathStops;
            this.pathDistance = pathDistance;
            this.duration = duration;
            this.remainingHubs = new LinkedList<>();
            this.rechargeStops = rechargeStops;
            this.hoursInformation = hoursInformation;
        }

        public Path() {
            this.pathStops = new LinkedList<>();
            this.pathDistance = 0.0;
            this.duration = 0;
            this.remainingHubs = new LinkedList<>();
            this.rechargeStops = new LinkedList<>();
            this.hoursInformation = new LinkedList<>();
        }

        public Path(Path other) {
            this.pathDistance = other.pathDistance;
            this.duration = other.duration;
            this.remainingHubs = new LinkedList<>(other.remainingHubs);
            this.pathStops = new LinkedList<>(other.pathStops);
            this.rechargeStops = new LinkedList<>(other.rechargeStops);
        }

        public double getPathDistance() {
            return pathDistance;
        }

        public LinkedList<Localidades> getPathStops() {
            return pathStops;
        }

        public LinkedList<Localidades> getRemainingHubs() {
            return remainingHubs;
        }

        public double getDuration() {
            return duration;
        }

        public LinkedList<Localidades> getRechargeStops() {
            return rechargeStops;
        }

        public void setPathDistance(double pathDistance) {
            this.pathDistance = pathDistance;
        }

        public void setPathStops(LinkedList<Localidades> pathStops) {
            this.pathStops = pathStops;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public void setRemainingHubs(LinkedList<Localidades> remainingHubs) {
            this.remainingHubs = remainingHubs;
        }

        public void setRechargeStops(LinkedList<Localidades> rechargeStops) {
            this.rechargeStops = rechargeStops;
        }

        public void addDuration(double duration) {
            this.duration += duration;
        }

        public void addPathStops(LinkedList<Localidades> newPathStops) {
            this.pathStops.addAll(newPathStops);
        }

        public void addRechargeStops(LinkedList<Localidades> newRechargeStops) {
            this.rechargeStops.addAll(newRechargeStops);
        }

        public void addRechargeStop(Localidades newRechargeStops) {
            this.rechargeStops.add(newRechargeStops);
        }

        public void addDistance(double pathDistance) {
            this.pathDistance += pathDistance;
        }
    }


