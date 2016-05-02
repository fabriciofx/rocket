package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.pessoa.Nome;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class GeradorNome {
	private static final int MIN_SOBRENOMES = 2;

	private static final String[] NOMES = { "Abel", "Abelardo", "Abrahão",
			"Abraão", "Abrão", "Abílio", "Ada", "Adalberto", "Adalgisa", "Adão",
			"Agatha", "Alana", "Alexandre", "Alice", "Alícia", "Amanda", "Ana",
			"Ana Beatriz", "Ana Carolina", "Ana Clara", "Ana Julia",
			"Ana Laura", "Ana Luiza", "Ana Lívia", "Ana Sophia", "André",
			"Anthony", "Antonella", "Antonio", "Arthur", "Augusto", "Babette",
			"Balbina", "Balraj", "Baltazar", "Barbie", "Bárbara", "Bartolomeu",
			"Basílio", "Beata", "Beatriz", "Benjamin", "Benício", "Bernardo",
			"Betina", "Bianca", "Brenda", "Breno", "Bruna", "Bruno", "Bryan",
			"Bárbara", "Cacilda", "Caetano", "Caio", "Calebe", "Calista",
			"Calixta", "Calixto", "Camellia", "Cameron", "Camila", "Camile",
			"Camélia", "Carlos Eduardo", "Carolina", "Caroline", "Catarina",
			"Cauã", "Cauê", "Cecília", "Clara", "Dafne", "Dagmar", "Dagmara",
			"Daiana", "Daiane", "Daisy", "Dalila", "Dalton", "Dalva", "Daniel",
			"Danilo", "Davi", "Diego", "Diogo", "Dália", "Débora", "Edgar",
			"Edite", "Edith.", "Edmundo", "Edna", "Eduarda", "Eduardo",
			"Elaine", "Elisa", "Eloá", "Elykaelle", "Emanuel", "Emanuelly",
			"Emily", "Enrico", "Enzo", "Enzo Gabriel", "Erick", "Ester",
			"Evelyn", "Fabiana", "Fabiano", "Fabrizio", "Fabrícia", "Fabrício",
			"Fabíola", "Fanny", "Felipe", "Fernanda", "Fernando", "Francisco",
			"Fábia", "Fábio", "Fátima", "Gabriel", "Gabriela", "Gabriele",
			"Gabriella", "Gabrielle", "Gabrielly", "Gaetano", "Ganesh", "Genji",
			"George", "Georgia", "Giovanna", "Giovanni", "Guilherme", "Gustavo",
			"Hadassa", "Hadrián", "Haideé", "Haidê", "Halima", "Hamilton",
			"Hannah", "Haydê", "Hebe", "Hector", "Heidi", "Heitor", "Helena",
			"Heloisa", "Henrique", "Henry", "Hugo", "Iago", "Ian", "Iara",
			"Idalina", "Ieda", "Iemanjá", "Ignácio", "Igor", "Ilsa", "Indra",
			"Inácio", "Isaac", "Isabel", "Isabella", "Isabelle", "Isadora",
			"Jaci", "Jacira", "Jacob", "Jacqueline", "Jacques", "Jacy",
			"Jacyra", "Jacó", "Jade", "Jaime", "Jennifer", "Joana", "Joaquim",
			"João", "João Gabriel", "João Guilherme", "João Lucas",
			"João Miguel", "João Paulo", "João Pedro", "João Vitor", "José",
			"Juan", "Julia", "Juliana", "Julio César", "Kaila", "Kaio",
			"Kaique", "Kalil", "Kalila", "Kamilly", "Kaori", "Karen", "Karim",
			"Karina", "Karine", "Karla", "Kevin", "Laerte", "Laila", "Lailah",
			"Lana", "Lara", "Larisa", "Larissa", "Laura", "Lavínia", "Laércio",
			"Laís", "Laísa", "Leonardo", "Letícia", "Levi", "Lorena", "Lorenzo",
			"Lourival", "Lourivaldo", "Luan", "Luana", "Lucas", "Lucas Gabriel",
			"Lucca", "Luiz Felipe", "Luiz Fernando", "Luiz Guilherme",
			"Luiz Gustavo", "Luiz Henrique", "Luiz Miguel", "Luiz Otávio",
			"Luiza", "Luna", "Lívia", "Mabel", "Madalena", "Mafalda", "Magali",
			"Magda", "Magdalena", "Magno", "Maia", "Maiara", "Maitê", "Manuela",
			"Marcela", "Marcelo", "Marcos Vinicius", "Maria", "Maria Alice",
			"Maria Cecília", "Maria Clara", "Maria Eduarda", "Maria Fernanda",
			"Maria Júlia", "Maria Luiza", "Maria Sophia", "Maria Vitória",
			"Mariah", "Mariana", "Mariane", "Marina", "Matheus",
			"Matheus Henrique", "Maíra", "Melissa", "Meninas", "Miguel",
			"Milena", "Mirella", "Mireilly", "Murilo", "Nadine", "Nadir",
			"Nadya", "Naila", "Nailah", "Nair", "Najma", "Nancy", "Naomi",
			"Nathan", "Natália", "Nicolas", "Nicole", "Nina", "Nádia",
			"Octávia", "Octávio", "Odete", "Odette", "Odila", "Ofélia", "Olavo",
			"Olga", "Olivia", "Olímpia", "Olímpio", "Otávio", "Pablo", "Paco",
			"Paloma", "Pamela", "Paola", "Paolo", "Pascoal", "Pasqual",
			"Patrícia", "Patrício", "Pedro", "Pedro Henrique", "PedroLucas",
			"Pietra", "Pietro", "Quincas", "Quitéria", "Rachel", "Rafael",
			"Rafaela", "Rafaella", "Raimundo", "Raj", "Rajesh", "Rani",
			"Raquel", "Raul", "Rayssa", "Raísa", "Raíssa", "Rebeca", "Renan",
			"Renato", "Ricardo", "Rodrigo", "Ryan", "Sabrina", "Sacha",
			"Safira", "Salim", "Salma", "Salomão", "Salomé", "Salvador",
			"Samanta", "Samuel", "Sarah", "Sophia", "Sophie", "Stefany",
			"Stella", "Sálvio", "Tabita", "Taciana", "Tadeu", "Takashi",
			"Tales", "Talita", "Tamara", "Tancredo", "Taís", "Thales", "Theo",
			"Thiago", "Tomás", "Tábata", "Ubaldo", "Ugo", "Ulisses", "Ulrika",
			"Umberto", "Urbano", "Vagner", "Valdemar", "Valdemir", "Valdir",
			"Valdo", "Valdomiro", "Valentim", "Valentina", "Valentino",
			"Vinicius", "Vitor", "Vitor Gabriel", "Vitor Hugo", "Vitória",
			"Wagner", "Waldemar", "Waldemir", "Waldemiro", "Waldir", "Waldo",
			"Waldomiro", "Waldyr", "Wallace", "Walmir", "Xavier", "Xaviera",
			"Yasmim", "Yasmin", "Yasmina", "Yasmine", "Yoko", "Yolanda", "Yone",
			"Yoná", "Yoshi", "Yukio", "Yuri", "Zacarias", "Zara", "Zenaide",
			"Zenon", "Zilda", "Zoe", "Zuleica", "Zuleika", "Zulmira", "Zélia",
			"Éder", "Édison", "Édson", "Ícaro", "Úlrica", "Úrsula" };

	private static final String[] SOBRENOMES = { "Abarca", "Albernaz",
			"Albuquerque", "Alcântara", "de Alcântara", "Almeida", "de Almeida",
			"Andrade", "de Andrade", "Antunes", "Alpoim", "Ávila", "Azevedo",
			"de Azevedo", "Avelar", "Arantes", "Araújo", "de Araújo", "Baldaya",
			"Barros", "de Barros", "Bettencourt", "Barcelos", "Boins", "Borges",
			"Brito", "de Brito", "Bruges", "Brum", "Cabral", "Cacena", "Câmara",
			"Camelo", "Campo", "Canto", "Cardoso", "Carvalhal", "Carvalho",
			"de Carvalho", "Carvão", "Castelo", "Castro", "de Castro", "Chaves",
			"Coelho", "Cordeiros", "Coronel", "Correia", "Corte", "Cota",
			"Coutinho", "Correa", "Diniz", "Drummond", "Dutra", "Dias",
			"Duarte", "Dornelles", "Damasceno", "Dias", "Enes", "Escobar",
			"Espínola", "Espinoza", "Estaco", "Evangelho", "Esteves", "Estrada",
			"Fagundes", "Falcão", "Faria", "Farinha", "Fagundes", "Ferraz",
			"Ferreira", "Ferro", "Figueira", "Figueiredo", "de Figueiredo",
			"Flores", "das Flores", "Fonseca", "Fournier", "Fontes", "Fraga",
			"Fragoso", "França", "Franco", "Furtado", "Freitas", "Freire",
			"Furtado", "Fortunato", "Godinho", "Gois", "Gomes", "Gonçalves",
			"Goulart", "Gouveia", "Guedes", "Guerra", "Guilherme", "Guimarães",
			"Gusmão", "Guterres", "Henriques", "Hipólito", "Horta", "Inácio",
			"Jardim", "Jesus", "de Jesus", "Junqueira", "de Junqueira", "Lima",
			"de Lima", "Luchini", "Lacerda", "de Lacerda", "Lago", "Laranjeira",
			"Leal", "Leão", "Leitão", "Leite", "Leme", "Lemos", "Lima",
			"de Lima", "Linhares", "Lira", "de Lira", "Lisboa", "Lobato",
			"Lobo", "Lopes", "Lousada", "Luz", "da Luz", "Macedo", "Machado",
			"Maciel", "Malory", "Madeira", "Madruga", "Magalhães", "Maia",
			"Marques", "Martins", "Matos", "Marinho", "Mariz", "Mascarenhas",
			"Medeiros", "Meira", "Meireles", "Melo", "de Melo", "Mendes",
			"Mendonça", "Meneses", "Merens", "Messias", "Mesquita", "Mesquita",
			"Miranda", "Moita", "Moniz", "Monjardino", "Monteiro", "Morais",
			"Moreira", "Mota", "Moura", "Muniz", "Mercado", "Nascimento",
			"Negreiro", "Neto", "Neves", "das Neves", "Nóbrega", "Noleto",
			"Noronha", "de Noronha", "Nunes", "Oliveira", "de Oliveira",
			"Ornelas", "Ortins", "Osório", "Van", "Pacheco", "Pais", "Paim",
			"Palhinha", "Pamplona", "Parreira", "Paiva", "de Paiva", "Paranhos",
			"Paredes", "Passos", "dos Passos", "Paz", "da Paz", "Pedroso",
			"Peixoto", "Pereira", "Pessoa", "Pimenta", "Pimentel", "Pinheiro",
			"Pires", "Pires", "Pita", "Pinheiro", "Pontes", "Porto", "Quadros",
			"Quaresma", "Queiroga", "Queirós", "Quintanilha", "Ramalho",
			"Ramires", "Ramos", "Rebelo", "Rêgo", "Reis", "Resende", "Ribeiro",
			"Rico", "Rio", "Rocha", "Rodovalho", "Rodrigues", "Rosa", "Rua",
			"Ramalho", "Sá", "de Sá", "Sacadura", "Salazar", "Sales", "Salgado",
			"Salgueiro", "Sampaio", "Sanches", "Sanfelicce", "Santos",
			"dos Santos", "Sardinha", "Saraiva", "Sarmento", "Seabra",
			"Séguier", "Seixas", "Sequeira", "de Sequeira", "Serpa", "da Silva",
			"Silva", "Silveira", "Silveira", "Siuve", "Silvano", "Simões",
			"Soares", "de Sousa", "Sousa", "Souto", "Tavoras", "Tavares",
			"Teixeira", "Teles", "Terra", "Teive", "Tevês", "Toledo", "Torres",
			"Uchoa", "Utra", "Valente", "Vargas", "Vasconcelos",
			"de Vasconcelos", "Vaz", "Veiga", "Veloso", "Ventura", "Venturoso",
			"Viana", "Viegas", "Vieira", "Willem", "Xavier", "Ximenes",
			"Zarco" };

	private final Aleatorio aleatorio;

	public GeradorNome() {
		aleatorio = new Aleatorio();
	}

	public Nome get() {
		return new Nome(com(2, 5));
	}

	public String getStringNome() {
		return NOMES[aleatorio.numero(NOMES.length)];
	}

	public String getStringSobrenome() {
		return SOBRENOMES[aleatorio.numero(SOBRENOMES.length)];
	}

	public String com(final int minSobrenomes, final int maxSobrenomes) {
		final StringBuilder sb = new StringBuilder();

		// A quantidade mínima de sobrenomes sempre será 2
		final int min = minSobrenomes < MIN_SOBRENOMES ? MIN_SOBRENOMES
				: minSobrenomes;
		final int max = maxSobrenomes <= minSobrenomes ? minSobrenomes
				: maxSobrenomes;

		// Apagando o StringBuilder
		sb.setLength(0);

		// Começa pelo nome
		sb.append(getStringNome());

		int numSobrenomes = aleatorio.numero(min, max);

		while (--numSobrenomes > 0) {
			sb.append(" ");
			sb.append(getStringSobrenome());
		}

		return sb.toString();
	}
}
