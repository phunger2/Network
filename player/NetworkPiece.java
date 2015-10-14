package player;

import player.NetworkBoard.PlayerColor;
import player.NetworkBoard.Location;

protected class NetworkPiece {
	
	// It will be an invariant that pieces can only be at valid locations.  It is NOT, however,
	// the job of this class to maintain this invariant.  This class will provide an update method
	// to change the location of the piece that will not check if the provided new location is a valid
	// one.  It will be an assumption that the location passed in is a valid one. 
	
	private NetworkBoard myBoard;
	private int x;
	private int y;
	protected PlayerColor color;
	protected Location myLocation;
	protected int pieceNumber;
	
	public NetworkPiece(PlayerColor color, int pieceNumber, NetworkBoard board) {
		x_coord = -1;
		y_coord = -1;
		this.color = color;
		myBoard = board;
		myLocation = Location.NOT_PLACED;
		this.pieceNumber = pieceNumber;
	}
	
	private boolean isPlaced() {
		return (myLocation != Location.NOT_PLACED);
	}
	
	protected void changeLocation(int new_x, int new_y) {
		
		x = new_x;
		y = new_y;
		
		if (myBoard.isGoalLocation(new_x_coord, new_y_coord, color)) {
			if (color == PlayerColor.BLACK) {
				if (new_y_coord == 0) {
					myLocation == Location.START_GOAL;
				} else {
					myLocation == Location.END_GOAL;
				}
			} else if (color == PlayerColor.WHITE) {
				if (new_x_coord == 0) {
					myLocation = Location.START_GOAL;
				} else {
					myLocation = Location.END_GOAL;
				}
			}
		} else {
			myLocation = Location.NORMAL;
		}
		
	}
	
	protected void resetPiece() {
		x_coord = -1;
		y_coord = -1;
		myLocation = Location.NOT_PLACED;
	}
	
	private boolean isMyLocation(int x_coord, int y_coord) {
		return (this.x_coord == x_coord) && (this.y_coord == y_coord);
	}
	
	private boolean sameRow(NetworkPiece piece) {
		return (this.y == piece.y);
	}
	
	private boolean sameColumn(NetworkPiece piece) {
		return (this.x == piece.x);
	}
	
	private boolean onDiagonal(NetworkPiece piece) {
		return (Math.abs(this.x - piece.x) == Math.abs(this.y - piece.y))
	}
	
	protected boolean canSee(NetworkPiece piece) {
		return ((this.isPlaced()) &&
				(piece.isPlaced()) &&
				(!this.equals(piece)) &&
				(sameRow(piece) || sameColumn(piece) || onDiagonal(piece)) &&
			    (notBlocked(piece)));
	}
	
	private boolean notBlocked(NetworkPiece piece) {
		
		int xIncrement;
		int yIncrement;
		
		if (this.x == piece.x) {
			xIncrement = 0;
		} else {
			xIncrement = (piece.x - this.x) - Math.abs(piece.x - this.x);
		}
		
		if (this.y == piece.y) {
			yIncrement = 0;
		} else { 
			yIncrement = (piece.y - this.y) - Math.abs(piece.y - this.y);
		}

		int i = this.x;
		int j = this.y;
		
		while (((this.x < piece.x) && (i >= this.x) && (i <= piece.x)) ||
			   ((this.x > piece.x) && (i <= this.x) && (i >= piece.x)) ||
			   ((this.y < piece.y) && (j >= this.y) && (j <= piece.y)) ||
			   ((this.y > piece.y) && (j <= this.y) && (j >= piece.y))) {
				
			if ((!(this.isMyLocation(i, j) || piece.isMyLocation(i, j))) && myBoard.getPieceAtLocation(i, j) != null) {
				return false;
			}
			
			i += xIncrement;
			j += yIncrement;
			
		}
		
		return true;
		
	}
	
	protected boolean isAdjacentTo(NetworkPiece piece) {
		return ((Math.abs(this.x - piece.x) <= 1) &&
				(Math.abs(this.y - piece.y) <= 1) &&
				(!this.equals(piece)));
	}
	
}