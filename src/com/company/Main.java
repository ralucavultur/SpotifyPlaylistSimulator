package com.company;

import java.util.*;

public class Main {
    private static LinkedList<Song> playList = new LinkedList<>();
    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Song rUMine = new Song("R U Mine", 3.27);
        Song arabella = new Song("Arabella", 2.57);
        Song doIWannaKnow = new Song("Do i wanna know", 3.00);

        Song ride = new Song("Ride", 4.00);
        Song laneBoy = new Song("Lane boy", 2.44);
        Song polarize = new Song("Polarize", 2.13);

        Song blueJeans = new Song("Blue jeans", 4.23);

        Album aM = new Album("AM");
        aM.addSong(rUMine);
        aM.addSong(arabella);
        aM.addSong(doIWannaKnow);
        aM.addSong(new Song("Knee Socks", 1.23));

        Album blurryFaces = new Album("Blurry Faces");
        blurryFaces.addSong(ride);
        blurryFaces.addSong(laneBoy);
        blurryFaces.addSong(polarize);

        albums.add(aM);
        albums.add(blurryFaces);

        addSongToPlaylist(rUMine);
        addSongToPlaylist(arabella);
        addSongToPlaylist(doIWannaKnow);
        addSongToPlaylist(ride);
        addSongToPlaylist(polarize);
        addSongToPlaylist(laneBoy);
        addSongToPlaylist(blueJeans);

//        printPlayList(playList);

        play(playList);

    }

    private static void printPlayList(LinkedList<Song> playList) {
        Iterator<Song> i = playList.iterator();
        System.out.println("The songs are: ");
        while (i.hasNext()) {
            System.out.println(i.next().getName());
        }
    }

    private static void play(LinkedList songs) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean moveForward = true;

        ListIterator<Song> listIterator = songs.listIterator();

        if (songs.isEmpty()) {
            System.out.println("Playlist is empty");
            return;
        } else {
            System.out.println("Now playing: " + listIterator.next().getName());
            printMenu();
        }
        while (!quit) {

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printMenu();
                    break;
                case 1:
                    if (!moveForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        moveForward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next().getName());
                    } else {
                        System.out.println("You've reached the end of the playlist");
                        moveForward = false;
                    }
                    break;
                case 2:
                    if (moveForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        moveForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous().getName());
                    } else {
                        System.out.println("You are at the top of the playlist");
                        moveForward = true;
                    }
                    break;
                case 3:
                    if (moveForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                            System.out.println("Now playing: " + listIterator.next().getName());
                        }
                    }
                    if (!moveForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                            System.out.println("Now playing: " + listIterator.previous().getName());
                        }
                    }
                    break;
                case 4:
                    printPlayList(songs);
                    break;
                case 5:
                    if (playList.size()>0) {
                        listIterator.remove();
                        System.out.println("Song successfully deleted.");
                        if (listIterator.hasNext()){
                            System.out.println("Now playing: " + listIterator.next().getName());
                        } else if (listIterator.hasPrevious()){
                            System.out.println("Now playing: " + listIterator.previous().getName());
                        }
                    } else {
                        System.out.println("Playlist is empty");
                    }
                    break;
                case 6:
                    quit= true;
            }
        }
    }

    private static void printMenu() {
        System.out.println("This are the choices:\npress" +
                "\n0-To print menu" +
                "\n1-To go to the next song" +
                "\n2-To go to the previous song" +
                "\n3-To replay current song" +
                "\n4-To show the list of songs" +
                "\n5-To remove current song" +
                "\n6-To quit");
    }

    private static void addSongToPlaylist(Song song) {
        for (int i = 0; i < albums.size(); i++) {
            for (int j = 0; j < albums.get(i).getSongs().size(); j++)
                if (albums.get(i).getSongs().get(j).getName().equals(song.getName())) {
                    playList.add(song);
                    return;
                }
        }
        System.out.println("Song is not part of any album");
    }

}
