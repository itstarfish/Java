### 2025-02-27 Žilvinas Balčiūnas, Arnas Zinkevičius grupė ISIT-21

import tkinter as tk
import time
from collections import deque

"""
    Ši klasė realizuoja Tower of Hanoi galvosūkių sprendimo grafinę sąsają (GUI) naudojant Tkinter biblioteką.
    Leidžia vartotojui vizualiai stebėti galvosūkio sprendimus pagal pasirinktinus paieškos algoritmus.
    """
class TowerOfHanoiGUI:
    # Inicializuoja Tower of Hanoi grafinę sąsają, nustato pradinę būseną, sukuria GUI elementus.
    def __init__(self, root, n_disks):
        self.root = root
        self.n_disks = n_disks  # Diskų skaičius
        self.state = [[i for i in range(n_disks, 0, -1)], [], []]  # Pradinė būsena
        self.solution = []  # Saugo sprendimo judesius
        self.move_count = 0  # Inicijuojame judesių skaičių
        self.canvas = tk.Canvas(root, width=600, height=400, bg="white")
        self.canvas.pack()

        self.create_rods()
        self.create_disks()

        # Sprendimo ir nustatymo mygtukai
        solve_btn = tk.Button(root, text="Išspręsti", command=self.start_solution)
        solve_btn.pack(side="left", padx=20)

        reset_btn = tk.Button(root, text="Nustatyti iš naujo", command=self.reset)
        reset_btn.pack(side="right", padx=20)

        # Laukas pasirinktam diskų skaičiui
        self.disk_count_entry = tk.Entry(root)
        self.disk_count_entry.insert(0, str(self.n_disks))  # Numatytoji reikšmė
        self.disk_count_entry.pack(side="top")

        # Etiketė, rodanti judesių skaičių
        self.move_label = tk.Label(root, text=f"Judėjimai: {self.move_count}")
        self.move_label.pack(side="top")

        # Lėtėjimo valdiklis
        self.delay_slider = tk.Scale(root, from_=0, to=2.0, resolution=0.1, orient="horizontal",
                                     label="Vėlavimas (sek.)")
        self.delay_slider.set(1)  # Numatytoji vėlavimo trukmė - 1 sekundė
        self.delay_slider.pack(side="top")

        # Išskleidžiamasis meniu pasirinkti paieškos algoritmą
        self.algorithms = ["DFS", "BFS"]
        self.algorithm_var = tk.StringVar(root)
        self.algorithm_var.set(self.algorithms[0])  # Numatytoji yra DFS
        algorithm_menu = tk.OptionMenu(root, self.algorithm_var, *self.algorithms)
        algorithm_menu.pack(side="top")

    """Sukurti stulpus (vietas, kur diskeliai gali būti sukrauti)."""
    def create_rods(self):
        rod_width = 10
        rod_height = 200
        center_x = [150, 300, 450]  # Stulpų centrų X koordinatės
        ground_y = 350

        self.rods = []  # Saugo stulpų centrus nuorodai
        for x in center_x:
            self.canvas.create_rectangle(
                x - rod_width // 2,
                ground_y - rod_height,
                x + rod_width // 2,
                ground_y,
                fill="black"
            )
            self.rods.append(x)

    """Sukuria grafinius diskus ir išdėsto juos ant pradinio stulpo pagal pradinę būseną."""
    def create_disks(self):
        ground_y = 350
        self.disk_ids = []  # Saugo grafinius diskų ID
        self.disk_text_ids = []  # Saugo tekstinius diskų dydžių žymenų ID
        self.disk_size_to_graphical_id = {}  # Susiejimas tarp disko dydžio ir grafinio ID
        disk_height = 20
        base_disk_width = 60  # Pradinis mažiausio disko pločio pagrindas

        # Sukuriame diskus pagal pradinę būseną
        for i, size in enumerate(self.state[0]):
            # Kiekvienas diskas daromas platesnis pagal jo dydį
            disk_width = base_disk_width + (size - 1) * 20  # Padidinti plotį didesniems diskams
            x_center = self.rods[0]  # 1-ojo stulpo X centras
            y_top = ground_y - (i + 1) * disk_height  # Išdėstome diską virš paskutiniojo

            # Sukuriame stačiakampį (diską) ir saugome jo ID disk_ids
            disk = self.canvas.create_rectangle(
                x_center - disk_width // 2,
                y_top,
                x_center + disk_width // 2,
                y_top + disk_height,
                fill=f"#{size * 3:02x}{size * 2:02x}3f",  # Spalva priklauso nuo dydžio
                tags="disk"  # Pažymime diską, kad būtų lengviau valdyti
            )
            self.disk_ids.append(disk)

            # Sukuriame teksto etiketę diskui su dydžiu
            disk_text = self.canvas.create_text(
                x_center,
                y_top + disk_height // 2,  # Pozicionuojame tekstą disko viduryje
                text=str(size),
                font=("Arial", 8, "bold"),
                fill="white"
            )
            self.disk_text_ids.append(disk_text)

            # Saugojame grafinio disko ID pagal šio disko dydį
            self.disk_size_to_graphical_id[size] = disk

    """Atkurti pradinę būseną."""
    def reset(self):
        try:
            self.n_disks = int(self.disk_count_entry.get())  # Gauti pasirinktinį diskų skaičių
            if self.n_disks < 1:
                raise ValueError("Diskų skaičius turi būti didesnis nei 0.")
        except ValueError as e:
            print(f"Neteisingas diskų skaičius: {e}")
            return

        self.state = [[i for i in range(self.n_disks, 0, -1)], [], []]
        self.move_count = 0  # Iš naujo nustatyti judesių skaičių
        self.move_label.config(text=f"Judėjimai: {self.move_count}")  # Atnaujinti judesių etiketę
        self.canvas.delete("all")  # Išvalyti visus drobės elementus
        self.create_rods()
        self.create_disks()

    """Gylio paieškos (DFS) sprendimas Tower of Hanoi su GUI animacija."""
    def solve_hanoi_dfs(self):
        
        self.move_count = 0
        self.move_label.config(text="Judėjimai: 0")

        # Paversti pradinę būseną į tuple, kad ji būtų nekintama
        initial_state = tuple(tuple(rod) for rod in self.state)
        goal_state = (tuple(), tuple(), tuple(range(self.n_disks, 0, -1)))  # Tikslo būsena

        stack = [(initial_state, [])]  # Stakas saugo būsenas ir jų judesių sekas (DFS naudoja staką)
        visited = set()  # Kad išvengtume vėlinių būsenų
        visited.add(initial_state)

        self.solution_found = False  # Atstatyti sprendimo radimo žymeklį
        self.total_moves_made = 0  # Sekti bendrą atliktų judesių skaičių per DFS procesą

        print(f"\n=== Pradedama DFS animacija ===")
        print(f"Pradinė būsena: {initial_state}\n")

        while stack and not self.solution_found:
            # Paimti paskutinę būseną apdoroti (LIFO pagal DFS)
            current_state, moves = stack.pop()

            # Sinchronizuoti self.state su dabartine DFS būsena GUI atvaizdavimui
            self.state = [list(rod) for rod in current_state]  # Sinchronizuojame self.state su logine DFS būsena

            # Atnaujinti GUI, kad atspindėtų dabartinę būseną
            for rod_index in range(3):  # Atnaujiname visus stulpus GUI
                self.update_rod_positions(rod_index)
            self.root.update_idletasks()  # Priversti UI atnaujinimą
            time.sleep(self.delay_slider.get())  # Pridėti vėlavimą tarp judesių

            # Derinimas: užfiksuoti dabartinę būseną ir judesius konsolėje
            ### print(f"Tyrinėjama būsena: {current_state} po judesių: {moves}")

            # Patikrinti, ar pasiekta tikslo būsena
            if current_state == goal_state:
                self.solution = moves  # Saugojame sprendimo kelią
                self.solution_found = True  # Nustatome žymeklį, kad sustotų tolesnis tyrinėjimas
                print(f"Sprendimas rastas: {self.solution}\n")
                break  # Nedelsiant sustoti DFS cikle, kai randamas sprendimas

            # Tyrinėti visus galimus judesius iš šios būsenos
            for source in range(3):
                for target in range(3):
                    if source != target and current_state[source]:  # Užtikrinti, kad šaltinio stulpas turi diskų
                        # Patikriname Tower of Hanoi taisyklę
                        if not current_state[target] or current_state[target][-1] > current_state[source][-1]:
                            # Sukuriame galimą naują būseną perkelus viršutinį diską
                            new_state = [list(rod) for rod in current_state]  # Kopijuojame stulpus
                            disk_to_move = new_state[source].pop()  # Pašaliname viršutinį diską iš šaltinio stulpo
                            new_state[target].append(disk_to_move)  # Padedame diską ant tikslinio stulpo

                            # Paverčiame į tuple, kad būtų nekintama
                            new_state_tuple = tuple(tuple(rod) for rod in new_state)

                            if new_state_tuple not in visited:
                                visited.add(new_state_tuple)  # Pažymime kaip aplankytą
                                stack.append((new_state_tuple, moves + [(source, target)]))  # Pridėti prie stack

                            # Atnaujinti GUI, kad atspindėtų judesį
                            self.move_disk(source, target)  # Atlikti ir vizualiai parodyti disko judėjimą
                            self.root.update_idletasks()  # Priversti GUI atnaujinimą
                            time.sleep(self.delay_slider.get())  # Pridėti vėlavimą tarp animacijų

                            # Padidinti judesių skaičiavimą per DFS procesą
                            self.move_count += 1
                            self.total_moves_made += 1  # Padidinti bendrą atliktų judesių skaičių
                            self.move_label.config(text=f"Judėjimai: {self.move_count}")
                            ### print(f"Judesių skaičius po judesio: {self.move_count}")

        # Kai DFS baigiasi, atspausdiname rezultatus
        if self.solution_found:
            # Rodyti optimaliam sprendimui reikalingų judesių skaičių
            min_moves_needed = (2 ** self.n_disks) - 1  # Minimalaus judesių skaičius Tower of Hanoi sprendimui
            print("--------------------------------------------------------------")
            print(f"Bendras atliktų judesių skaičius per DFS paiešką: {self.total_moves_made}")
            print(f"Minimalus judesių skaičius sprendimui pasiekti (optimalus sprendimas): {min_moves_needed}")
            print("--------------------------------------------------------------")
        else:
            print("Sprendimas nerastas!")

    """Plotinio paieškos (BFS) sprendimas Tower of Hanoi su GUI animacija."""
    def solve_hanoi_bfs(self):
        
        self.move_count = 0
        self.move_label.config(text="Judėjimai: 0")
        # Paverčiame pradinę būseną į tuple, kad ji būtų nekintama
        initial_state = tuple(tuple(rod) for rod in self.state)
        goal_state = (tuple(), tuple(), tuple(range(self.n_disks, 0, -1)))  # Tikslo būsena

        queue = deque([(initial_state, [])])  # Eilė saugo būsenas ir jų judesių sekas
        visited = set()  # Kad išvengtume vėlinių būsenų
        visited.add(initial_state)

        self.solution_found = False  # Užtikrinti, kad sprendimo žymeklis būtų atstatytas
        self.total_moves_made = 0  # Sekti bendrą atliktų judesių skaičių per BFS

        print(f"\n=== Pradedama BFS animacija ===")
        print(f"Pradinė būsena: {initial_state}\n")

        while queue and not self.solution_found:
            # Pašalinti kitą būseną apdoroti
            current_state, moves = queue.popleft()

            # Sinchronizuoti self.state su dabartine BFS būsena GUI atvaizdavimui
            ### print(f"Tyrinėjama būsena: {current_state} po judesių: {moves}")
            self.state = [list(rod) for rod in current_state]  # Sinchronizuojame self.state su logine BFS būsena

            # Atnaujinti GUI, kad atspindėtų dabartinę būseną
            for rod_index in range(3):  # Atnaujiname visus stulpus, jei reikia
                self.update_rod_positions(rod_index)
            self.root.update_idletasks()  # Priversti UI atnaujinimą
            time.sleep(self.delay_slider.get())  # Pridėti vėlavimą tarp judesių

            # Patikrinti, ar pasiekta tikslo būsena
            if current_state == goal_state:
                self.solution = moves  # Saugojame sprendimo kelią
                self.solution_found = True
                print(f"Sprendimas rastas: {self.solution}\n")
                break  # Sustoti BFS cikle, kai randamas sprendimas

            # Tyrinėti visus galimus judesius iš šios būsenos
            for source in range(3):
                for target in range(3):
                    if source != target and current_state[source]:  # Užtikrinti, kad šaltinio stulpas turi diskų
                        # Patikriname Tower of Hanoi taisyklę
                        if not current_state[target] or current_state[target][-1] > current_state[source][-1]:
                            # Sukuriame galimą naują būseną perkelus viršutinį diską
                            new_state = [list(rod) for rod in current_state]  # Kopijuojame stulpus
                            disk_to_move = new_state[source].pop()  # Pašaliname viršutinį diską iš šaltinio stulpo
                            new_state[target].append(disk_to_move)  # Padedame diską ant tikslinio stulpo

                            # Paverčiame į tuple, kad būtų nekintama
                            new_state_tuple = tuple(tuple(rod) for rod in new_state)

                            if new_state_tuple not in visited:
                                visited.add(new_state_tuple)  # Pažymime kaip aplankytą
                                queue.append((new_state_tuple, moves + [(source, target)]))  # Pridėti prie eilės

                            # DEBUG žinutė: parodyti užsigrūdintą būseną
                            ### print(f"Pridėta nauja būsena į eilę: {new_state_tuple} | Judesiai: {moves + [(source, target)]}")

                            # Atnaujinti GUI, kad atspindėtų judesį
                            self.move_disk(source, target)  # Atlikti ir vizualiai parodyti disko judėjimą
                            self.root.update_idletasks()  # Priversti GUI atnaujinimą
                            time.sleep(self.delay_slider.get())  # Pridėti vėlavimą tarp animacijų

                            # Padidinti judesių skaičiavimą
                            self.move_count += 1
                            self.total_moves_made += 1  # Padidinti bendrą atliktų judesių skaičių
                            self.move_label.config(text=f"Judėjimai: {self.move_count}")
                            ### print(f"Judesių skaičius po judesio: {self.move_count}")

        # Kai BFS baigiasi, atspausdiname rezultatus
        if self.solution_found:
            # Rodyti optimaliam sprendimui reikalingų judesių skaičių
            min_moves_needed = (2 ** self.n_disks) - 1  # Minimalaus judesių skaičius Tower of Hanoi sprendimui
            print("--------------------------------------------------------------")
            print(f"Bendras atliktų judesių skaičius per BFS paiešką: {self.total_moves_made}")
            print(f"Minimalus judesių skaičius sprendimui pasiekti (optimalus sprendimas): {min_moves_needed}")
            print("--------------------------------------------------------------")
            # Atlikti animaciją ir parodyti judesius
            for move in self.solution:
                source, target = move
                # Atnaujinti GUI ir animuoti judesį
                ### print(f"Perkeliant diską iš stulpo {source} į stulpą {target}")
                self.move_disk(source, target)
                self.root.update_idletasks()  # Priversti GUI atnaujinimą
                time.sleep(self.delay_slider.get())  # Pridėti vėlavimą tarp animacijų

            print("Animacija baigta! Galutinė būsena:", self.state)
        else:
            print("Sprendimas nerastas!")

    """Animuoti diskų judėjimą pagal sprendimo žingsnius."""
    def animate_solution(self):
        # Pirmiausia, atstatyti būseną ir GUI į pradinę būseną
        self.state = [[i for i in range(self.n_disks, 0, -1)], [], []]
        self.move_count = 0  # Atstatyti judesių skaičių
        self.move_label.config(text=f"Judėjimai: {self.move_count}")
        self.canvas.delete("all")
        self.create_rods()
        self.create_disks()

        # Nustatyti vėlavimą pagal slankiklį
        delay = self.delay_slider.get()

        # Atlikti sprendimo judesius žingsnis po žingsnio
        for move in self.solution:
            src, tgt = move

            # Išspausdinti derinimo informaciją PRIEŠ judesį
            ### print(f"Perkeliant diską iš stulpo {src} į stulpą {tgt}")
            ### print(f"Prieš judesį - Būsena: {self.state}")

            # Atlikti disko judėjimą (loginį ir vizualinį sinchronizavimą)
            self.move_disk(src, tgt)

            # Išspausdinti derinimo informaciją PO judesio
            ### print(f"Po judesio - Būsena: {self.state}")

            # Padidinti judesių skaičiavimą
            self.move_count += 1
            self.move_label.config(text=f"Judėjimai: {self.move_count}")

            # Pauzė, kad judėjimas būtų matomas vizualiai
            time.sleep(delay)

        print(f"Animacija baigta! Galutinė būsena: {self.state}")

    """Išspręsti Tower of Hanoi galvosūkį ir animuoti žingsnius pagal pasirinktą algoritmą."""
    def start_solution(self):
        self.solution = []  # Išvalyti seną sprendimą

        # Gauti pasirinktą algoritmą
        selected_algorithm = self.algorithm_var.get()

        if selected_algorithm == "DFS":
            self.solve_hanoi_dfs()  # Išspręsti naudojant DFS
        elif selected_algorithm == "BFS":
            self.solve_hanoi_bfs()  # Išspręsti naudojant BFS

        # self.animate_solution()  # Animuoti sprendimo žingsnius

    """Pagal vartotojo pasirinktą algoritmą (DFS arba BFS) pradeda problemos sprendimą."""
    def move_disk(self, source, target):
        ### print(f"Perkeliant diską iš Stulpo {source} į Stulpą {target}")
        ### print(f"Prieš judesį - Būsena: {self.state}")  # Derinimo tikslas: Išspausdinti būseną prieš judesį
        if not self.state[source]:
            return  # Nėra disko, kurį reikėtų perkelti

        # Pašalinti viršutinį diską iš šaltinio stulpo (disko dydis)
        disk = self.state[source].pop()  # Pašalinti viršutinį diską iš šaltinio stulpo būsenos
        self.state[target].append(disk)  # Pridėti diską prie tikslo stulpo būsenos

        ### print(f"Po judesio - Būsena: {self.state}")  # Derinimo tikslas: Išspausdinti būseną po judesio

        # Rasti grafinį disko ID pagal disko dydį
        disk_id = self.disk_ids[disk - 1]  # Disko dydis atitinka indeksą disk_ids sąraše
        disk_text_id = self.disk_text_ids[disk - 1]  # Teksto ID šiam disko dydžiui

        # Išspausdinti koordinates prieš atnaujinimą
        # print(f"Prieš atnaujinant koordinates - Diskas {disk} yra {self.canvas.bbox(disk_id)}")

        # Tikslo stulpo x koordinates
        rod_x = self.rods[target]
        ground_y = 350  # Pagrindinė y koordinatė, kur yra stulpai
        disk_height = 20  # Kiekvieno disko aukštis

        # Apskaičiuoti Y poziciją pagal tai, kiek diskų šiuo metu yra tikslo stulpe
        target_y = ground_y - (len(self.state[target]) + 1) * disk_height  # Diskai sukraunami vertikaliai

        # Gauti disko plotį pagal jo dydį
        disk_width = 60 + (disk - 1) * 20  # Padidinti disko plotį pagal dydį

        # Atnaujinti disko grafines koordinates
        self.canvas.coords(
            disk_id,
            rod_x - disk_width // 2,  # Kairė x
            target_y,  # Viršutinė y
            rod_x + disk_width // 2,  # Dešinė x
            target_y + disk_height  # Apatinė y
        )

        # Perkelti teksto etiketę (dydį) į tas pačias naujas koordinates
        self.canvas.coords(
            disk_text_id,
            rod_x,
            target_y + disk_height // 2  # Išdėstyti tekstą disko centre
        )

        # Atnaujinti tekstą, kad rodytų teisingą dydį (disko dydis visada pasiekiamas 'disk')
        self.canvas.itemconfig(disk_text_id, text=str(disk))

        # Išspausdinti koordinates po atnaujinimo
        # print(f"Po atnaujinimo koordinates - Diskas {disk} yra {self.canvas.bbox(disk_id)}")

        # Derinimo tikslas: Išspausdinti atnaujintas disko koordinates
        updated_coords = self.canvas.bbox(disk_id)
        # print(f"Atnaujintos koordinates Diskui {disk} (ID {disk_id}): {updated_coords}")

        # Po judesio, atnaujinti likusių diskų pozicijas šaltinio stulpe
        self.update_rod_positions(source)
        self.update_rod_positions(target)  # Užtikrinti, kad abu stulpai būtų atnaujinti

        # Priversti atnaujinti GUI, kad pakeitimai būtų taikomi iš karto
        self.canvas.update_idletasks()  # Priverčia piešinį atnaujinti visus laukiančius uždavinius
        self.root.update()  # Priverčia pagrindinį langą atnaujinti

        # Pridėti mažą pauzę, kad judesys būtų matomas vizualiai
        time.sleep(0.2)  # Koreguokite miego laiką, kad kontroliuotumėte animacijos greitį

    """Atnaujina grafinius diskų išdėstymus nurodytame stulpe."""
    def update_rod_positions(self, rod):
        rod_x = self.rods[rod]
        ground_y = 350  # Pagrindinė y koordinatė, kur yra stulpai
        disk_height = 20  # Kiekvieno disko aukštis

        # Atnaujinti Y koordinatę kiekvienam diskui stulpelyje
        for index, disk in enumerate(self.state[rod]):
            disk_id = self.disk_ids[disk - 1]  # Gauti grafinį disko ID
            disk_text_id = self.disk_text_ids[disk - 1]  # Gauti teksto ID šiam diskui
            disk_width = 60 + (disk - 1) * 20  # Disko plotis pagal jo dydį

            # Apskaičiuoti Y koordinatę (diskai sukraunami nuo apačios į viršų)
            target_y = ground_y - (index + 1) * disk_height  # Teisinga sukrovimo tvarka

            # Atnaujinti disko poziciją
            self.canvas.coords(
                disk_id,
                rod_x - disk_width // 2,  # Kairė x
                target_y,  # Viršutinė y
                rod_x + disk_width // 2,  # Dešinė x
                target_y + disk_height  # Apatinė y
            )

            # Atnaujinti teksto poziciją (disko dydis)
            self.canvas.coords(
                disk_text_id,
                rod_x,
                target_y + disk_height // 2  # Išdėstyti tekstą disko centre
            )

            # Atnaujinti tekstą, kad atitiktų teisingą disko dydį
            self.canvas.itemconfig(disk_text_id, text=str(disk))

            # Derinimo tikslas: Išspausdinti koordinates po atnaujinimo
            updated_coords = self.canvas.bbox(disk_id)
            # print(f"Diskas {disk} (ID {disk_id}) naujos koordinatės po atnaujinimo: {updated_coords}")


if __name__ == "__main__":
    root = tk.Tk()
    root.title("Tower of Hanoi")  # Pavadinimas "Tower of Hanoi"
    TowerOfHanoiGUI(root, n_disks=3)  # Pradėti su 3 diskais pagal numatytuosius nustatymus
    root.mainloop()  # Paleisti pagrindinę programą

