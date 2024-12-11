import java.util.*;

public class Ejercito {
    private String nombreReino;
    private ArrayList<Soldado> soldados;
    private boolean evolucionRealizada; 
    private static int contEjercitos=0;

    public Ejercito(String nombreReino) {
        contEjercitos++;
        this.nombreReino = nombreReino;
        this.soldados = new ArrayList<>();
    }

    public void generarSoldados(int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad; i++) {
            int tipo = random.nextInt(4); 
            Soldado soldado;
    
            switch (tipo) {
                case 0 -> soldado = new Espadachin(
                    "Espadachin " + i + " (" + nombreReino + ")", 
                    random.nextInt(3) + 8, 
                    10, 
                    8,  
                    random.nextInt(10), 
                    random.nextInt(10)
                );
                case 1 -> soldado = new Arquero(
                    "Arquero " + i + " (" + nombreReino + ")",
                    random.nextInt(3) + 3, 
                    7,  
                    3,  
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextInt(10) 
                );
                case 2 -> soldado = new Caballero(
                    "Caballero " + i + " (" + nombreReino + ")",
                    random.nextInt(3) + 10, 
                    13, 
                    7,  
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextBoolean() 
                );
                case 3 -> soldado = new Lancero(
                    "Lancero " + i + " (" + nombreReino + ")",
                    random.nextInt(3) + 5, 
                    5,  
                    10, 
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextDouble() * 2 + 1 
                );
                default -> throw new IllegalArgumentException("Tipo de soldado desconocido.");
            }
    
            soldados.add(soldado);
        }
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public String getNombreReino() {
        return nombreReino;
    }

    public static int getContEjercitos() {
        return contEjercitos;
    }
    
    public void aplicarBeneficios(String territorio) {
        boolean tieneBeneficio = switch (this.nombreReino) {
            case "Reinos Escandinavos" -> territorio.equals("Bosque");
            case "Reinos Anglo-Sajones" -> territorio.equals("Campo Abierto");
            case "Reino de Asturias" -> territorio.equals("Montaña");
            case "Imperio Bizantino" -> territorio.equals("Playa");
            case "Imperio Carolingio" -> territorio.equals("Bosque") || territorio.equals("Campo Abierto");
            case "Reino de los Búlgaros" -> territorio.equals("Montaña") || territorio.equals("Desierto");
            default -> false;
        };

        if (tieneBeneficio) {
            for (Soldado soldado : soldados) {
                soldado.aumentarVida(1);
            }
            System.out.println("El ejército de " + nombreReino + " recibe beneficios por el territorio: " + territorio);
        }
    }
    
    public void mostrarSoldados() {
        System.out.println("Soldados del Ejército de " + nombreReino + ":");
        for (Soldado soldado : soldados) {
            System.out.println(soldado.getClass().getSimpleName() + ": " + soldado);
        }
    }

    public void generarUnidadesEspeciales() {
        Random random = new Random();
        int fila, columna;
        boolean posicionValida;

        do {
            fila = random.nextInt(10);  
            columna = random.nextInt(10);  

            posicionValida = true;
            for (Soldado s : soldados) {
                if (s.getFila() == fila && s.getColumna() == columna) {
                    posicionValida = false;
                    break;
                }
            }
        } while (!posicionValida);

        Soldado unidadEspecial;
        switch (nombreReino) {
            case "Reinos Escandinavos" -> unidadEspecial = new EspadachinTeutonico(
                "Espadachin Teutónico (" + nombreReino + ")", 
                12, 10, 10, fila, columna
            );
            case "Reinos Anglo-Sajones" -> unidadEspecial = new Arquero(
                "Arquero Anglo-Sajón (" + nombreReino + ")", 
                10, 9, 6, fila, columna, 10
            );
            case "Reino de Asturias" -> unidadEspecial = new EspadachinConquistador(
                "Espadachin Conquistador (" + nombreReino + ")", 
                11, 10, 9, fila, columna
            );
            case "Imperio Bizantino" -> unidadEspecial = new Caballero(
                "Caballero Bizantino (" + nombreReino + ")", 
                13, 12, 8, fila, columna, true
            );
            case "Imperio Carolingio" -> unidadEspecial = new CaballeroFranco(
                "Caballero Franco (" + nombreReino + ")", 
                12, 13, 7, fila, columna
            );
            case "Reino de los Búlgaros" -> unidadEspecial = new CaballeroMoro(
                "Caballero Moro (" + nombreReino + ")", 
                13, 14, 6, fila, columna
            );
            default -> throw new IllegalArgumentException("Reino desconocido: " + nombreReino);
        }

        soldados.add(unidadEspecial);
    }
    
    public boolean isEvolucionRealizada() {
        return evolucionRealizada;
    }

    public void setEvolucionRealizada(boolean evolucionRealizada) {
        this.evolucionRealizada = evolucionRealizada;
    }

    public void intentarEvolucionar() {
        int contEjercitos = 2;
        if (evolucionRealizada) {
            System.out.println("El ejército de " + nombreReino + " ya realizó su evolución.");
            return;
        }

        for (int i = 0; i < soldados.size(); i++) {
            Soldado soldado = soldados.get(i);
            if (soldados.get(i).getNumEjercito() == 1) {
                contEjercitos = 1;
            }

            switch (nombreReino) {
                case "Reinos Escandinavos" -> {
                    if (soldado instanceof Espadachin) {
                        soldados.set(i, new EspadachinTeutonico(
                            "Espadachin Teutónico (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército de Reinos Escandinavos ha evolucionado un Espadachin a Espadachin Teutónico.");
                        return;
                    }
                }
                case "Reinos Anglo-Sajones" -> {
                    if (soldado instanceof Arquero) {
                        soldados.set(i, new Arquero(
                            "Arquero Élite (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque() + 2, 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna(), 
                            15
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército de Reinos Anglo-Sajones ha evolucionado un Arquero a Arquero Élite.");
                        return;
                    }
                }
                case "Reino de Asturias" -> {
                    if (soldado instanceof Espadachin) {
                        soldados.set(i, new EspadachinConquistador(
                            "Espadachin Conquistador (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército del Reino de Asturias ha evolucionado un Espadachin a Espadachin Conquistador.");
                        return;
                    }
                }
                case "Imperio Bizantino" -> {
                    if (soldado instanceof Caballero) {
                        soldados.set(i, new Caballero(
                            "Caballero Élite (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque() + 3, 
                            soldado.getDefensa() + 1, 
                            soldado.getFila(), 
                            soldado.getColumna(), 
                            true
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército del Imperio Bizantino ha evolucionado un Caballero a Caballero Élite.");
                        return;
                    }
                }
                case "Imperio Carolingio" -> {
                    if (soldado instanceof Caballero) {
                        soldados.set(i, new CaballeroFranco(
                            "Caballero Franco (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército del Imperio Carolingio ha evolucionado un Caballero a Caballero Franco.");
                        return;
                    }
                }
                case "Reino de los Búlgaros" -> {
                    if (soldado instanceof Caballero) {
                        soldados.set(i, new CaballeroMoro(
                            "Caballero Moro (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército del Reino de los Búlgaros ha evolucionado un Caballero a Caballero Moro.");
                        return;
                    }
                }
                default -> throw new IllegalArgumentException("Reino desconocido: " + nombreReino);
            }
        }

        System.out.println("No hay soldados elegibles para evolucionar en el ejército de " + nombreReino + ".");
    }
}
