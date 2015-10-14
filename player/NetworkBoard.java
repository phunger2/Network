package player;

import java.util.*;

public class NetworkBoard {
	
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLUMNS = 10;
	private static final int NUM_PIECES = 10;
	private static final int NETWORK_LENGHT = 6;
	private static final int MAX_ADJACENT_PIECES = 3;
	
	private int blackPiecesPlayed;
	private int whitePiecesPlayed;
	private NetworkPiece[][] gameBoard;
	private Connection[][] visibilityGraph;
	private Connection[][] adjacencyGraph;
	private ArrayList<NetworkPiece> blackPieces;
	private ArrayList<NetworkPiece> whitePieces;
	private PlayerColor playerTurn;
	
	
	public enum PlayerColor {
		WHITE, BLACK
	}
	
	public enum Location {
		START_GOAL, END_GOAL, NORMAL, NOT_PLACED
	}
	
	public enum Connection {
		NOT_CONNECTED, CONNECTED
	}
	
	public NetworkBoard() {
		
		//Initialize the game board.
		gameBoard = new NetworkPiece[numColumns][numRows];
		for (int x_coord = 0; x_coord < numColumns; x_coord++) {
			for (int y_coord = 0; y_coord < numRows; y_coord++) {
				gameBoard[x_coord][y_coord] = null;
			}
		}
		
		//Initialize the pieces and the variables for keeping track of the numbers of pieces played.
		blackPiecesPlayed = 0;
		whitePiecesPlayed = 0;
		blackPieces = new ArrayList<NetworkPiece>();
		whitePieces = new ArrayList<NetworkPiece>();
		for (int i = 0; i < numPieces; i++) {
			NetworkPiece newBlackPiece = new NetworkPiece(PlayerColor.BLACK, i, this);
			NetworkPiece newWhitePiece = new NetworkPiece(PlayerColor.WHITE, i, this);
			blackPieces.add(newBlackPiece);
			whitePieces.add(newWhitePiece);
		}
		
		//Initialize the connection graphs
		visibilityGraph = new Connection[numPieces][numPieces];
		adjacencyGraph = new Connection[numPieces][numPieces];
		for (int i = 0; i < numPieces; i++) {
			for (int j = 0; j < numPieces; j++) {
				visibilityGraph[i][j] = Connection.NOT_CONNECTED;
				adjacencyGraph[i][j] = Connection.NOT_CONNECTED;
			}
		}
		
		playerTurn = PlayerColor.WHITE;
		
	}
	
	public ArrayList<Move> getValidMoves() {
		
		ArrayList<NetworkPiece> pieces;
		int numPiecesPlayed;
		ArrayList<Move> validMoves = new ArrayList<Move>();
		
		if (playerTurn == PlayerColor.WHITE) {
			pieces = whitePieces;
			numPiecesPlayed = whitePiecesPlayed;
		} else {
			pieces = blackPieces;
			numPiecesPlayed = blackPiecesPlayed;
		}
		
		if (numPiecesPlayed < NUM_PIECES) {
			// Only add moves
			(for int i = 0; i < NUM_COLUMNS; i++) {
				for (int j = 0; j < NUM_ROWS; j++) {
					if (isLocationAddable(i,j)) {
						//add the add move for this location to the list
					}
				}
			}
		} else {
			(for int i = 0; i < NUM_PIECES; i++) {
				(for int j = 0; j < NUM_COLUMNS; j++) {
					(for int k = 0; k < NUM_ROWS; k++) {
						if (isLocationSteppable(j, k, pieces.get(i)) {
							//create a step move and add it to the list
						}
					}
				}
			}
		}
		
		return validMoves;
		
	}
	
	private boolean isLocationAddable(int x, int y) {
		
	}
	
	private boolean isLocationSteppable(int x, int y, NetworkPiece stepPiece) {
		
	}
	
	//Returns the other player.
	private PlayerColor getOtherPlayer() {
		if (playerTurn == PlayerColor.BLACK) {
			return PlayerColor.WHITE;
		} else {
			return PlayerColor.BLACK;
		}
	}
	
	//Returns whether or not the given (x,y) coordinates are a goal location for the given player.
	private boolean isGoalLocation(int x_coord, int y_coord, PlayerColor color) {
		if (color == PlayerColor.WHITE) {
			return ((x_coord == 0) || (x_coord == 9)) && isValidLocation(x_coord, y_coord);
		} else {
			return ((y_coord == 0) || (y_coord == 9)) && isValidLocation(x_coord, y_coord);
		}
	}
	
	//Returns whether or not the given (x,y) coordinates are a location you could place a chip (black or white).
	private boolean isValidLocation(int x_coord, int y_coord) {
		return (!(((x_coord == 0) && (y_coord == 0)) ||
			      ((x_coord == numColumns - 1) && (y_coord == 0)) ||
				  ((x_coord == 0) && (y_coord == numRows - 1)) ||
				  ((x_coord == numColumns - 1) && (y_coord == numRows - 1))) &&
			    ((x_coord >= 0) && (x_coord <= numColumns - 1)) &&
				((y_coord >= 0) && (y_coord <= numRows - 1)));
	}
	
	//Returns whether or not the player whos turn it is can play a piece of their color at the given (x,y) coordinates.
	private boolean isLocationPlayable(int x_coord, int y_coord, PlayerColor color) {
		return (isValidLocation(x_coord, y_coord) && 
			    (gameBoard[x_coord][y_coord] == null) && 
			    !(isGoalLocation(x_coord, y_coord, getOtherPlayer(color))));
	}
	

	
	private boolean adjacencyDFS(int currentDepth, )
	
	private boolean doMove(Move m) {
		// check to see if he spot is a valid spot (i.e. not one of the corners, not goal area of other player, 
		// not an area off the chart, not an occupied spot, and see if putting a piece at this location will
		// create a cluster of 3 adjacent pieces (obviously excluding the piece being stepped in the case of step
		// moves)).  If all requirements are satisfied, perform the move and update all fields as necessary and return true.
		// Otherwise do nothing and return false.
		if (m.moveKind = Move.ADD) {
			
		} else if (m.moveKind = Move.QUIT) {
			
		}
	}
	
	private boolean undoMove(Move m) {
		
	}
	
	public boolean networkExists(PlayerColor color) {
		
		int numPiecesPlayed;
		ArrayList<NetworkPiece> pieces;
		boolean result;
		ArrayList<NetworkPiece> network;
		
		if (color == PlayerColor.BLACK) {
			numPiecesPlayed = blackPiecesPlayed;
			pieces = blackPieces;
		} else if (color == PlayerColor.WHITE) {
			numPiecesPlayed = whitePiecesPlayed;
			pieces = whitePieces;
		}
			
		if (numPiecesPlayed >= NETWORK_LENGTH) {}
			for (int i = 0; i < numPiecesPlayed; i++) {
				NetworkPiece piece = pieces.get(i);
				if (piece.location() == Location.START_GOAL) {
					network = new ArrayList<NetworkPiece>();
					network.add(piece);
					result = newtorkDFS(1, network);
					if (result == true) {
						//network will be the first network found
						break;
					}
				}
			}
		} else {
			result = false;
		}
		
		return result;
			
	}
	
	private boolean networkDFS(int currentDepth, ArrayList<NetworkPiece> currentNetwork, ArrayList<NetworkPiece> pieces) {
		NetworkPiece lastPiece = currentNetwork.get(currentDepth - 1);
		if ((currentDepth >= NETWORK_LENGTH) && (lastPiece.myLocation == END_GOAL)) {
			return true;
		} else {
			for (int i = 0; i < NUM_PIECES; i++) {
				if (visibilityGraph[lastPiece][i] == Connection.CONNECTED) {
					currentNetwork.add()pieces.get(i));
					result = networkDFS(currentDepth + 1, currentNetwork, pieces);
					if (result == true) {
						break;
					} else {
						currentNetwork.removeLast();
					}
				}
			}
			return false
		}
	}
	
}