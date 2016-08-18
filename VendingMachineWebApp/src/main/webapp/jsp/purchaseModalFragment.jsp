<div class="modal fade" id="purchaseModal" tabindex="-1" role="dialog"
     aria-labelledby="purchaseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="purchaseModalLabel">Purchase Details</h4>
            </div>
            <div class="modal-body" id="question">
            </div>
            <div class="modal-footer">
                <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="buy-button" class="btn btn-default"
                                    data-dismiss="modal">
                                Purchase
                            </button>
                            <button type="button" class="btn btn-default  buy_btn"
                                    data-dismiss="modal">
                                Cancel
                            </button>
                            <input type="hidden" id="purchase-item-id">
                        </div>
            </div>
        </div>
    </div>
</div>  